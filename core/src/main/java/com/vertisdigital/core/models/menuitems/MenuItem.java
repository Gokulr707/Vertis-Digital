package com.vertisdigital.core.models.menuitems;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class MenuItem {

    @Inject
    private String title;

    @Inject
    private String url;

    @Inject
    private List<MenuItem> subMenuPages;

    @Inject
    private List<MenuItem> nestedSubMenuPages;

    public MenuItem(String title, String url, List<MenuItem> subMenuPages, List<MenuItem> nestedSubMenuPages) {
        this.title = title;
        this.url = url;
        this.subMenuPages = subMenuPages;
        this.nestedSubMenuPages = nestedSubMenuPages;
    }

    public MenuItem() {
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuItem> getSubMenuPages() {
        return subMenuPages;
    }

    public void setSubMenuPages(List<MenuItem> subMenuPages) {
        this.subMenuPages = subMenuPages;
    }

    public List<MenuItem> getNestedSubMenuPages() {
        return nestedSubMenuPages;
    }

    public void setNestedSubMenuPages(List<MenuItem> nestedSubMenuPages) {
        this.nestedSubMenuPages = nestedSubMenuPages;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", subMenuPages=" + subMenuPages +
                ", nestedSubMenuPages=" + nestedSubMenuPages +
                '}';
    }
}
