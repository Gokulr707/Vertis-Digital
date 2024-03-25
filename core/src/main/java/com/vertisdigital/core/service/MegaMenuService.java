package com.vertisdigital.core.service;

import com.vertisdigital.core.models.menuitems.MenuItem;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import java.util.List;

public interface MegaMenuService {
    List<MenuItem> processChildPages(Resource parentResource, ResourceResolver resolver);

    List<MenuItem> processSubChildPages(Resource parentResource, ResourceResolver resolver);

    /// Execute the following method for [ Hide in Navigation – Hide this page only in the menu and submenu ]
    List<MenuItem> hideCurrentPage(Resource resource, ResourceResolver resolver, String currentTitle);

    /// Execute the following method for [ Hide all subpage in Navigation - Hide all pages under this page in the menu and
    //submenu. ]
    List<MenuItem> hideAllMenuSubMenuPages(Resource resource, ResourceResolver resolver, String currentTitle);
}
