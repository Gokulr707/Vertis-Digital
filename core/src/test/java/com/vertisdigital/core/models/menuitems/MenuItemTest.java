package com.vertisdigital.core.models.menuitems;

import com.vertisdigital.core.models.menuitems.MenuItem;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class MenuItemTest {

    @Mock
    private Resource resource;

    @Mock
    private SlingHttpServletRequest request;

    @Mock
    private ModelFactory modelFactory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConstructorAndGetters() {
        // Test constructor and getters
        String title = "Menu Item";
        String url = "/menu-item";
        List<MenuItem> subMenuPages = new ArrayList<>();
        List<MenuItem> nestedSubMenuPages = new ArrayList<>();

        MenuItem menuItem = new MenuItem(title, url, subMenuPages, nestedSubMenuPages);

        assertEquals(title, menuItem.getTitle());
        assertEquals(url, menuItem.getUrl());
        assertEquals(subMenuPages, menuItem.getSubMenuPages());
        assertEquals(nestedSubMenuPages, menuItem.getNestedSubMenuPages());
    }

    @Test
    public void testSetters() {
        // Test setters
        MenuItem menuItem = new MenuItem();
        String title = "New Menu Item";
        String url = "/new-menu-item";
        List<MenuItem> subMenuPages = new ArrayList<>();
        List<MenuItem> nestedSubMenuPages = new ArrayList<>();

        menuItem.setTitle(title);
        menuItem.setUrl(url);
        menuItem.setSubMenuPages(subMenuPages);
        menuItem.setNestedSubMenuPages(nestedSubMenuPages);

        assertEquals(title, menuItem.getTitle());
        assertEquals(url, menuItem.getUrl());
        assertEquals(subMenuPages, menuItem.getSubMenuPages());
        assertEquals(nestedSubMenuPages, menuItem.getNestedSubMenuPages());
    }

    @Test
    public void testToString() {
        // Test toString() method
        String title = "Menu Item";
        String url = "/menu-item";
        List<MenuItem> subMenuPages = new ArrayList<>();
        List<MenuItem> nestedSubMenuPages = new ArrayList<>();

        MenuItem menuItem = new MenuItem(title, url, subMenuPages, nestedSubMenuPages);

        String expectedString = "MenuItem{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", subMenuPages=" + subMenuPages +
                ", nestedSubMenuPages=" + nestedSubMenuPages +
                '}';

        assertEquals(expectedString, menuItem.toString());
    }

    @Test
    public void testDefaultConstructor() {
        // Test default constructor
        MenuItem menuItem = new MenuItem();
        assertNull(menuItem.getTitle());
        assertNull(menuItem.getUrl());
        assertNull(menuItem.getSubMenuPages());
        assertNull(menuItem.getNestedSubMenuPages());
    }

    @Test
    public void testModelFactoryCreation() {
        // Test creation of MenuItem using ModelFactory
        String title = "Menu Item";
        String url = "/menu-item";
        List<MenuItem> subMenuPages = new ArrayList<>();
        List<MenuItem> nestedSubMenuPages = new ArrayList<>();

        MenuItem menuItem = new MenuItem(title, url, subMenuPages, nestedSubMenuPages);

        when(modelFactory.createModel(resource, MenuItem.class)).thenReturn(menuItem);

        MenuItem menuItemFromModelFactory = modelFactory.createModel(resource, MenuItem.class);

        assertEquals(title, menuItemFromModelFactory.getTitle());
        assertEquals(url, menuItemFromModelFactory.getUrl());
        assertEquals(subMenuPages, menuItemFromModelFactory.getSubMenuPages());
        assertEquals(nestedSubMenuPages, menuItemFromModelFactory.getNestedSubMenuPages());
    }
}
