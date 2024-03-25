package com.vertisdigital.core.service.impl;

import com.day.crx.JcrConstants;
import com.vertisdigital.core.models.menuitems.MenuItem;
import com.vertisdigital.core.service.MegaMenuService;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * The Class GetMegaMenuServiceImpl.
 */
@Component(service = MegaMenuService.class, immediate = true)
public class MegaMenuServiceImpl implements MegaMenuService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String JCR_TITLE = "jcr:title";
    public static final String JCR_CONTENT = "jcr:content";
    public static final String CQ_PAGE_CONTENT = "cq:PageContent";
    public static final String DEFAULT_ROOT_PATH = "/content/vertisdigital";


    @Override
    public List<MenuItem> processChildPages(Resource parentResource, ResourceResolver resolver) {
        List<MenuItem> menuItems = new ArrayList<>();
        if(parentResource!=null) {
            Iterator<Resource> iterator = parentResource.listChildren();
            while (iterator!= null && iterator.hasNext()) {
                Resource child = iterator.next();
                String childPath = child.getPath();
                if (childPath.contains(JCR_CONTENT))
                    continue;
                Resource childPageContent = child.getChild(JcrConstants.JCR_CONTENT);

                if (childPageContent != null) {
                    ValueMap childPageProperties = childPageContent.getValueMap();
                    if(childPageProperties!=null) {
                        String jcrPrimaryType = childPageProperties.get(JcrConstants.JCR_PRIMARYTYPE, String.class);

                        if (StringUtils.isNotBlank(jcrPrimaryType) && CQ_PAGE_CONTENT.equals(jcrPrimaryType)) {
                            String jcrNodePath = child.getChild(JcrConstants.JCR_CONTENT).getPath();
                            Resource childNode = resolver.getResource(jcrNodePath);
                            ValueMap childNodeProperties = childNode.getValueMap();
                            String title = childNodeProperties.get(JCR_TITLE, String.class);
                            Resource resource = resolver.getResource(childPath);
                            MenuItem childItem = new MenuItem();
                            childItem.setTitle(title);
                            childItem.setUrl(childPath);
                            logger.info("Processing child item with title: {} and URL: {}", title, childPath);

                            List<MenuItem> subMenuPagesList = processSubChildPages(resource, resolver);
                            childItem.setSubMenuPages(subMenuPagesList);

                            menuItems.add(childItem);
                        }
                    }
                } else {
                    logger.warn("Child page content is null for path: {}", childPath);
                }
            }
        }
        return menuItems;
    }

    @Override
    public List<MenuItem> processSubChildPages(Resource parentResource, ResourceResolver resolver) {
        List<MenuItem> subChildList = new ArrayList<>();
        if(parentResource!=null) {
            Iterator<Resource> subIterator = parentResource.listChildren();
            while (subIterator !=null && subIterator.hasNext()) {
                Resource subChild = subIterator.next();
                String subChildPath = subChild.getPath();
                Resource resource = resolver.getResource(subChildPath);
                Resource subChildPageContent = resource.getChild(JcrConstants.JCR_CONTENT);

                if (subChildPageContent != null) {
                    ValueMap subChildPageProperties = subChildPageContent.getValueMap();
                    String subJcrPrimaryType = subChildPageProperties.get(JcrConstants.JCR_PRIMARYTYPE, String.class);

                    if (CQ_PAGE_CONTENT.equals(subJcrPrimaryType)) {
                        String subJcrNodePath = subChild.getChild(JcrConstants.JCR_CONTENT).getPath();
                        Resource subChildNode = resolver.getResource(subJcrNodePath);
                        ValueMap subChildNodeProperties = subChildNode.getValueMap();
                        String subPageTitle = subChildNodeProperties.get(JCR_TITLE, String.class);
                        logger.info("Processing child item with title: {} and URL: {}", subPageTitle, subChildPath);

                        MenuItem subChildItem = new MenuItem();
                        subChildItem.setTitle(subPageTitle);
                        subChildItem.setUrl(subChildPath);

                        subChildList.add(subChildItem);
                    }
                } else {
                    logger.warn("Child page content is null for path: {}", subChildPath);
                }
            }
        }

        return subChildList;
    }

    /// Execute the following method for [ Hide in Navigation – Hide this page only in the menu and submenu ]
   @Override
   public List<MenuItem> hideCurrentPage(Resource resource, ResourceResolver resolver, String currentTitle) {
        List<MenuItem> hideMenuSubMenuItems = new ArrayList<>();
        List<MenuItem> hideMenuSubMenu = processChildPages(resource, resolver);

        Iterator<MenuItem> iterator = hideMenuSubMenu.iterator();
        while (iterator.hasNext()) {
            MenuItem item = iterator.next();
            String existingPageTitle = item.getTitle();

            if (existingPageTitle.equals(currentTitle)) {
                // Remove the item from the current list
                iterator.remove();
                logger.info("Hiding menu item with title: {} at path: {}", existingPageTitle, item.getUrl());
            } else {
                // Check and remove from subpages as well
                List<MenuItem> subMenuItems = item.getSubMenuPages();
                List<MenuItem> updatedSubPages = new ArrayList<>();

                for (MenuItem subMenuItem : subMenuItems) {
                    String subPageTitle = subMenuItem.getTitle();
                    if (!subPageTitle.equals(currentTitle)) {
                        // If title is not equal, add it to the updated list
                        updatedSubPages.add(subMenuItem);
                    } else {
                        logger.info("Hiding sub-menu item with title: {} at path: {}", subPageTitle, subMenuItem.getUrl());
                    }
                }
                item.setSubMenuPages(updatedSubPages);
                hideMenuSubMenuItems.add(item);
            }
        }
        return hideMenuSubMenuItems;
    }

    /// Execute the following method for [ Hide all subpage in Navigation - Hide all pages under this page in the menu and
    //submenu. ]
    @Override
    public List<MenuItem> hideAllMenuSubMenuPages(Resource resource, ResourceResolver resolver, String currentTitle) {
        List<MenuItem> hideAllMenuSubMenuItems = new ArrayList<>();
        List<MenuItem> hideAllMenuSubMenu = processChildPages(resource, resolver);

        Iterator<MenuItem> iterator = hideAllMenuSubMenu.iterator();
        while (iterator.hasNext()) {
            MenuItem item = iterator.next();
            String existingPageTitle = item.getTitle();

            if (existingPageTitle.equals(currentTitle)) {
                item.setSubMenuPages(Collections.emptyList());
                logger.info("Hiding all sub-menu pages for menu item with title: {} at path: {}", existingPageTitle, item.getUrl());
            } else {
                // Keep the item and update sub-pages if needed
                List<MenuItem> subMenuItems = item.getSubMenuPages();
                List<MenuItem> updatedSubPages = new ArrayList<>();

                for (MenuItem subMenuItem : subMenuItems) {
                    String subPageTitle = subMenuItem.getTitle();
                    if (!subPageTitle.equals(currentTitle)) {
                        // If title is not equal, add it to the updated list
                        updatedSubPages.add(subMenuItem);
                    } else {
                        logger.info("Hiding sub-menu item with title: {} at path: {}", subPageTitle, subMenuItem.getUrl());
                    }
                }

                item.setSubMenuPages(updatedSubPages);
                hideAllMenuSubMenuItems.add(item);
            }
        }
        return hideAllMenuSubMenuItems;
    }

}
