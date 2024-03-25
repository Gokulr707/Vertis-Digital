package com.vertisdigital.core.models;
import com.day.cq.wcm.api.Page;
import com.vertisdigital.core.models.MegaMenuModel;
import com.vertisdigital.core.models.menuitems.MenuItem;
import com.vertisdigital.core.service.MegaMenuService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MegaMenuModelTest {

    @Mock
    Resource resource;

    @Mock
    ResourceResolver resolver;

    @Mock
    SlingHttpServletRequest request;

    @Mock
    Page currentPage;


    @Mock
    MegaMenuService megaMenuService;

    @ValueMapValue
    String pagePath;

    @SlingObject
    SlingHttpServletRequest slingRequest;

    @InjectMocks
    private MegaMenuModel megaMenuModel;

    @Before
    public void setUp() {
        megaMenuModel = new MegaMenuModel();
        megaMenuModel.currentPage = currentPage;
        megaMenuModel.request = request;
        megaMenuModel.megaMenuService = megaMenuService;
    }


    @Test
    public void testInit_WithBlankPagePath() {
        megaMenuModel.init();

    }

    @Test
    public void testHandleNavigationBar_WithNonNullCurrentPage() {
        when(currentPage.getPath()).thenReturn("/content/mypage");
        when(resolver.getResource(anyString())).thenReturn(resource);
        when(resource.getChild(anyString())).thenReturn(resource);
        when(resource.getValueMap()).thenReturn(Mockito.mock(org.apache.sling.api.resource.ValueMap.class));

        megaMenuModel.handleNavigationBar(resource, resolver);

    }

    @Test
    public void testHandleNavigationBar_WithNullCurrentPage() {
        megaMenuModel.handleNavigationBar(resource, resolver);

    }

    @Test
    public void testGetMegaMenuNavigationBar() {
        List<MenuItem> expectedMenuItems = new ArrayList<>();
        // Add expected menu items

        megaMenuModel.setMegaMenuNavigationBar(expectedMenuItems);

        List<MenuItem> result = megaMenuModel.getMegaMenuNavigationBar();

        assertEquals(expectedMenuItems, result);
    }

    @Test
    public void testSetAndGetPagePath() {
        String expectedPagePath = "/content/mypage";
        megaMenuModel.setPagePath(expectedPagePath);

        String result = megaMenuModel.getPagePath();

        assertEquals(expectedPagePath, result);
    }

    @Test
    public void testSetAndGetMegaMenuService() {
        MegaMenuService expectedService = Mockito.mock(MegaMenuService.class);
        megaMenuModel.setMegaMenuService(expectedService);

        MegaMenuService result = megaMenuModel.getMegaMenuService();

        assertEquals(expectedService, result);
    }

    @Test
    public void testSetAndGetHideNavigation() {
        boolean expectedHideNavigation = true;
        megaMenuModel.isHideNavigation(expectedHideNavigation);

        boolean result = megaMenuModel.isHideNavigation(false);

        assertEquals(!expectedHideNavigation, result);
    }

    @Test
    public void testSetAndGetHideAllSubpageInNavigation() {
        boolean expectedHideAllSubpageInNavigation = true;
        megaMenuModel.isHideAllSubpageInNavigation(expectedHideAllSubpageInNavigation);

        boolean result = megaMenuModel.isHideAllSubpageInNavigation(false);

        assertEquals(!expectedHideAllSubpageInNavigation, result);
    }

}
