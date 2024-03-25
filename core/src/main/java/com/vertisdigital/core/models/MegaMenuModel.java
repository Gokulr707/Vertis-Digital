package com.vertisdigital.core.models;


import com.day.cq.wcm.api.Page;
import com.day.crx.JcrConstants;
import com.vertisdigital.core.models.menuitems.MenuItem;
import com.vertisdigital.core.service.MegaMenuService;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class MegaMenuModel {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String JCR_TITLE = "jcr:title";

    @ValueMapValue
    String pagePath;

    @Inject
    Page currentPage;

    @SlingObject
    SlingHttpServletRequest request;

    @Inject
    transient MegaMenuService megaMenuService;
    @Inject
    private boolean hideNavigation ;
    @Inject
    private boolean hideAllSubpageInNavigation ;

    private List<MenuItem> megaMenuNavigationBar = new ArrayList<>();

    @PostConstruct
    protected void init() {
        if (StringUtils.isNotBlank(pagePath) && request!=null) {
            ResourceResolver pageResolver = request.getResourceResolver();
            Resource pageResource = pageResolver.getResource(pagePath);

            if (pageResource != null) {
                handleNavigationBar(pageResource, pageResolver);
            }
        } else {
            logger.info("RootPath is Null: {}", pagePath);
        }
    }

    void handleNavigationBar(Resource resource, ResourceResolver resolver) {
        if(currentPage!=null) {
            String currentPagePath = currentPage.getPath();
            if (currentPagePath != null) {
                Resource currentResource = Objects.requireNonNull(resolver.getResource(currentPagePath)).getChild(JcrConstants.JCR_CONTENT);
                if (currentResource != null) {
                    ValueMap valueMap = Objects.requireNonNull(currentResource).getValueMap();
                    String currentTitle = valueMap.get(JCR_TITLE, String.class);
                    if(StringUtils.isNotBlank(currentTitle)) {
                        String hideInNavigationStr = String.valueOf(valueMap.get("hideNavigation"));
                        hideNavigation = Boolean.parseBoolean(hideInNavigationStr);
                        logger.info("hideInNavigation: {}", hideNavigation);

                        String hideAllSubpageInNavigationStr = String.valueOf(valueMap.get("hideAllSubpageInNavigation"));
                        hideAllSubpageInNavigation = Boolean.parseBoolean(hideAllSubpageInNavigationStr);
                        logger.info("hideAllSubpageInNavigation: {}", hideAllSubpageInNavigation);

                        if (hideNavigation ) {
                            megaMenuNavigationBar = megaMenuService.hideCurrentPage(resource, resolver, currentTitle);
                        } else if (hideAllSubpageInNavigation ) {
                            megaMenuNavigationBar = megaMenuService.hideAllMenuSubMenuPages(resource, resolver, currentTitle);
                        } else {
                            megaMenuNavigationBar = megaMenuService.processChildPages(resource, resolver);

                        }
                    }
                }
            }
        }
    }


    public List<MenuItem> getMegaMenuNavigationBar() {
        return megaMenuNavigationBar;
    }

    public void setMegaMenuNavigationBar(List<MenuItem> megaMenuNavigationBar) {
        this.megaMenuNavigationBar = megaMenuNavigationBar;
    }

    public String getPagePath() {
        return pagePath;
    }
    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public boolean isHideNavigation(boolean b) {
        return hideNavigation;
    }

    public boolean isHideAllSubpageInNavigation(boolean b) {
        return hideAllSubpageInNavigation;
    }
    public MegaMenuService getMegaMenuService() {
        return megaMenuService;
    }

    public void setMegaMenuService(MegaMenuService megaMenuService) {
        this.megaMenuService = megaMenuService;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    public void setHideAllSubpageInNavigation(boolean expectedHideAllSubpageInNavigation) {
    }
}
