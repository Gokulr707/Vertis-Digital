/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.sling.scripting.sightly.apps.vertisdigital.components.megamenu;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class megamenu_html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_menumodel = null;
Object _global_page = null;
Object _global_template = null;
Object _global_emptydata = null;
Collection var_collectionvar0_list_coerced$ = null;
Collection var_collectionvar14_list_coerced$ = null;
_global_menumodel = renderContext.call("use", com.vertisdigital.core.models.MegaMenuModel.class.getName(), obj());
_global_page = renderContext.call("use", com.adobe.cq.wcm.core.components.models.Page.class.getName(), obj());
_global_template = renderContext.call("use", "core/wcm/components/commons/v1/templates.html", obj());
_global_emptydata = renderContext.getObjectModel().resolveProperty(_global_menumodel, "pagePath");
if (renderContext.getObjectModel().toBoolean(_global_emptydata)) {
    out.write("\r\n\r\n    <!-- Add your HTML structure here -->\r\n\r\n    <nav class=\"navmenu\">\r\n        ");
    {
        Object var_collectionvar0 = renderContext.getObjectModel().resolveProperty(_global_menumodel, "megaMenuNavigationBar");
        {
            long var_size1 = ((var_collectionvar0_list_coerced$ == null ? (var_collectionvar0_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar0)) : var_collectionvar0_list_coerced$).size());
            {
                boolean var_notempty2 = (var_size1 > 0);
                if (var_notempty2) {
                    {
                        long var_end5 = var_size1;
                        {
                            boolean var_validstartstepend6 = (((0 < var_size1) && true) && (var_end5 > 0));
                            if (var_validstartstepend6) {
                                out.write("<ul>");
                                if (var_collectionvar0_list_coerced$ == null) {
                                    var_collectionvar0_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar0);
                                }
                                long var_index7 = 0;
                                for (Object child : var_collectionvar0_list_coerced$) {
                                    {
                                        boolean var_traversal9 = (((var_index7 >= 0) && (var_index7 <= var_end5)) && true);
                                        if (var_traversal9) {
                                            out.write("\r\n            <li>\r\n                <a");
                                            {
                                                String var_attrcontent10 = (renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(child, "url"), "uri")) + ".html");
                                                {
                                                    Object var_shoulddisplayattr11 = ((renderContext.getObjectModel().toBoolean(var_attrcontent10) ? var_attrcontent10 : ("false".equals(var_attrcontent10))));
                                                    if (renderContext.getObjectModel().toBoolean(var_shoulddisplayattr11)) {
                                                        out.write(" href=\"");
                                                        out.write(renderContext.getObjectModel().toString(var_attrcontent10));
                                                        out.write("\"");
                                                    }
                                                }
                                            }
                                            out.write(">");
                                            {
                                                Object var_12 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(child, "title"), "text");
                                                out.write(renderContext.getObjectModel().toString(var_12));
                                            }
                                            out.write("</a>\r\n                ");
                                            {
                                                Object var_testvariable13 = renderContext.getObjectModel().resolveProperty(child, "subMenuPages");
                                                if (renderContext.getObjectModel().toBoolean(var_testvariable13)) {
                                                    out.write("\r\n                    ");
                                                    {
                                                        Object var_collectionvar14 = renderContext.getObjectModel().resolveProperty(child, "subMenuPages");
                                                        {
                                                            long var_size15 = ((var_collectionvar14_list_coerced$ == null ? (var_collectionvar14_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar14)) : var_collectionvar14_list_coerced$).size());
                                                            {
                                                                boolean var_notempty16 = (var_size15 > 0);
                                                                if (var_notempty16) {
                                                                    {
                                                                        long var_end19 = var_size15;
                                                                        {
                                                                            boolean var_validstartstepend20 = (((0 < var_size15) && true) && (var_end19 > 0));
                                                                            if (var_validstartstepend20) {
                                                                                out.write("<ul>");
                                                                                if (var_collectionvar14_list_coerced$ == null) {
                                                                                    var_collectionvar14_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar14);
                                                                                }
                                                                                long var_index21 = 0;
                                                                                for (Object submenu : var_collectionvar14_list_coerced$) {
                                                                                    {
                                                                                        boolean var_traversal23 = (((var_index21 >= 0) && (var_index21 <= var_end19)) && true);
                                                                                        if (var_traversal23) {
                                                                                            out.write("\r\n                        <li><a");
                                                                                            {
                                                                                                String var_attrcontent24 = (renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(submenu, "url"), "uri")) + ".html");
                                                                                                {
                                                                                                    Object var_shoulddisplayattr25 = ((renderContext.getObjectModel().toBoolean(var_attrcontent24) ? var_attrcontent24 : ("false".equals(var_attrcontent24))));
                                                                                                    if (renderContext.getObjectModel().toBoolean(var_shoulddisplayattr25)) {
                                                                                                        out.write(" href=\"");
                                                                                                        out.write(renderContext.getObjectModel().toString(var_attrcontent24));
                                                                                                        out.write("\"");
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            out.write(">");
                                                                                            {
                                                                                                Object var_26 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(submenu, "title"), "text");
                                                                                                out.write(renderContext.getObjectModel().toString(var_26));
                                                                                            }
                                                                                            out.write("</a></li>\r\n                    ");
                                                                                        }
                                                                                    }
                                                                                    var_index21++;
                                                                                }
                                                                                out.write("</ul>");
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        var_collectionvar14_list_coerced$ = null;
                                                    }
                                                    out.write("\r\n                ");
                                                }
                                            }
                                            out.write("\r\n            </li>\r\n        ");
                                        }
                                    }
                                    var_index7++;
                                }
                                out.write("</ul>");
                            }
                        }
                    }
                }
            }
        }
        var_collectionvar0_list_coerced$ = null;
    }
    out.write("\r\n    </nav>\r\n\r\n\r\n\r\n");
}
out.write("\r\n\r\n<style>\r\n    body {\r\n        margin: 0;\r\n        padding: 0;\r\n        font-family: sans-serif;\r\n    }\r\n\r\n    a {\r\n        text-decoration: none;\r\n    }\r\n\r\n    .navmenu {\r\n        border: 1px solid red;\r\n        border-radius: 13px;\r\n\r\n        box-shadow: rgba(50, 50, 93, 0.25) 0px 50px 100px -20px, rgba(0, 0, 0, 0.3) 0px 30px 60px -30px;\r\n    }\r\n\r\n    .navmenu ul {\r\n        padding-left: 0;\r\n        margin: 0;\r\n        list-style: none;\r\n        display: flex; /* Display items horizontally */\r\n        justify-content: space-around;\r\n    }\r\n\r\n    .navmenu li {\r\n        display: inline-block;\r\n        position: relative;\r\n    }\r\n\r\n    .navmenu a {\r\n        color: #0a0a0a;\r\n        display: block;\r\n        padding: 20px 15px;\r\n        border-bottom: 3px solid transparent;\r\n        transition: color 0.3s, border-bottom 0.3s;\r\n    }\r\n\r\n    .navmenu li:hover > a {\r\n        color: #E63946; /* Change color on hover */\r\n        border-bottom: 3px solid #E63946; /* Change border color on hover */\r\n    }\r\n\r\n    .navmenu ul ul {\r\n        display: none;\r\n        position: absolute;\r\n        top: 100%;\r\n        left: 0;\r\n        font-size: 13px;\r\n        width: 100%;\r\n        opacity: 0; /* Initially hidden */\r\n        transition: opacity 0.3s ease; /* Smooth transition */\r\n    }\r\n\r\n    .navmenu ul li:hover > ul {\r\n        display: block;\r\n        opacity: 1; /* Show submenu */\r\n    }\r\n\r\n    .navmenu ul ul li {\r\n        display: block;\r\n    }\r\n\r\n    /* Animation for submenu */\r\n    @keyframes fadeIn {\r\n        from { opacity: 0; }\r\n        to { opacity: 1; }\r\n    }\r\n\r\n    /* Apply animation to submenu */\r\n    .navmenu ul ul {\r\n        animation: fadeIn 0.3s ease;\r\n    }\r\n\r\n    /* Responsive Styles */\r\n    @media (max-width: 768px) {\r\n        .navmenu ul {\r\n            flex-direction: column;\r\n        }\r\n\r\n        .navmenu li {\r\n            width: 100%;\r\n            text-align: center;\r\n        }\r\n\r\n        .navmenu ul ul {\r\n            position: static;\r\n            background: none;\r\n            opacity: 1; /* Show submenu */\r\n        }\r\n\r\n        .navmenu ul ul li {\r\n            display: none;\r\n            border-bottom: 1px solid #ddd;\r\n            margin: 0;\r\n        }\r\n\r\n        .navmenu ul li:hover > ul li {\r\n            display: block;\r\n        }\r\n    }\r\n</style>\r\n");
{
    Object var_templatevar27 = renderContext.getObjectModel().resolveProperty(_global_template, "placeholder");
    {
        boolean var_templateoptions28_field$_isempty = (!renderContext.getObjectModel().toBoolean(_global_emptydata));
        {
            java.util.Map var_templateoptions28 = obj().with("isEmpty", var_templateoptions28_field$_isempty);
            callUnit(out, renderContext, var_templatevar27, var_templateoptions28);
        }
    }
}


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

