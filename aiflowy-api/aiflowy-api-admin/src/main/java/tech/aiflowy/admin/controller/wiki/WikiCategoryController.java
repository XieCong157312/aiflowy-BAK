package tech.aiflowy.admin.controller.wiki;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aiflowy.common.annotation.UsePermission;
import tech.aiflowy.common.web.controller.BaseCurdController;
import tech.aiflowy.wiki.entity.WikiCategory;
import tech.aiflowy.wiki.service.WikiCategoryService;

@RequestMapping("/api/v1/wikiCategory")
@RestController
@UsePermission(moduleName = "/api/v1/wiki")
public class WikiCategoryController extends BaseCurdController<WikiCategoryService, WikiCategory> {

    public WikiCategoryController(WikiCategoryService service) {
        super(service);
    }

}
