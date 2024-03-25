package com.vertisdigital.core.service.impl;

import com.vertisdigital.core.models.menuitems.MenuItem;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MegaMenuServiceImplTest {

    @Mock
    Resource parentResource;

    @Mock
    ResourceResolver resolver;

    @InjectMocks
    MegaMenuServiceImpl megaMenuService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testProcessChildPages() {
        // Mocking child resources
        Resource child1 = mock(Resource.class);
        Resource child2 = mock(Resource.class);
        List<Resource> children = new ArrayList<>();
        children.add(child1);
        children.add(child2);

        // Mocking iterator behavior
        Iterator<Resource> iterator = children.iterator();
        when(parentResource.listChildren()).thenReturn(iterator);

        // Mocking behavior of getResource for each child
        when(child1.getPath()).thenReturn("/content/child1");
        when(child2.getPath()).thenReturn("/content/child2");
        when(child1.getChild(anyString())).thenReturn(mock(Resource.class));
        when(child2.getChild(anyString())).thenReturn(mock(Resource.class));


        List<MenuItem> result = megaMenuService.processChildPages(parentResource, resolver);

        // Asserting the result
        assertEquals(0, result.size()); // Assuming that no menu items are processed in this test case
    }

    @Test
    void testProcessSubChildPages() {
        // Mocking behavior of getResource for the sub child
        Resource subChild = mock(Resource.class);
        when(resolver.getResource(anyString())).thenReturn(subChild);
        when(subChild.getChild(anyString())).thenReturn(mock(Resource.class));

        List<MenuItem> result = megaMenuService.processSubChildPages(parentResource, resolver);

        // Asserting the result
        assertEquals(0, result.size()); // Assuming that no menu items are processed in this test case
    }

    @Test
    void testHideCurrentPage() {
        // Mocking behavior of processChildPages

        List<MenuItem> result = megaMenuService.hideCurrentPage(parentResource, resolver, "Current Title");

        // Asserting the result
        assertEquals(0, result.size()); // Assuming that no menu items are processed in this test case
    }

    @Test
    void testHideAllMenuSubMenuPages() {
        // Mocking behavior of processChildPages

        List<MenuItem> result = megaMenuService.hideAllMenuSubMenuPages(parentResource, resolver, "Current Title");

        // Asserting the result
        assertEquals(0, result.size()); // Assuming that no menu items are processed in this test case
    }
}
