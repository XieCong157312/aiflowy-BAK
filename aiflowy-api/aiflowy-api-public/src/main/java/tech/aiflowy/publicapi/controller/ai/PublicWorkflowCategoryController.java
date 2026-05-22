package tech.aiflowy.publicapi.controller.ai;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aiflowy.ai.entity.WorkflowCategory;
import tech.aiflowy.ai.service.WorkflowCategoryService;
import tech.aiflowy.common.web.controller.BaseCurdController;

/**
 * 工作流分类
 *
 * @author michael
 * @since 2024-08-23
 */
@RestController
@RequestMapping("/public-api/v1/workflowCategory")
public class PublicWorkflowCategoryController extends BaseCurdController<WorkflowCategoryService, WorkflowCategory> {

    public PublicWorkflowCategoryController(WorkflowCategoryService service) {
        super(service);
    }

}