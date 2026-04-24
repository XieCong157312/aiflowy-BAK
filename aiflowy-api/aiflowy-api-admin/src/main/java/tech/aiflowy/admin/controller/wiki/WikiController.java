package tech.aiflowy.admin.controller.wiki;

import com.agentsflex.core.file2text.File2TextUtil;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.apache.tika.io.FilenameUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aiflowy.ai.agentsflex.ocrModel.OcrModel;
import tech.aiflowy.ai.entity.Model;
import tech.aiflowy.ai.service.ModelService;
import tech.aiflowy.common.constant.enums.EnumOcrTaskStatus;
import tech.aiflowy.common.constant.enums.EnumRecognitionMode;
import tech.aiflowy.common.constant.enums.EnumWikiType;
import tech.aiflowy.common.domain.Result;
import tech.aiflowy.common.filestorage.FileStorageService;
import tech.aiflowy.common.tree.Tree;
import tech.aiflowy.common.web.controller.BaseCurdController;
import tech.aiflowy.common.web.exceptions.BusinessException;
import tech.aiflowy.wiki.entity.Wiki;
import tech.aiflowy.wiki.service.WikiService;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@RequestMapping("/api/v1/wiki")
@RestController
public class WikiController extends BaseCurdController<WikiService, Wiki> {

    @Resource(name = "default")
    private FileStorageService storageService;
    @Resource
    private ModelService modelService;

    public WikiController(WikiService service) {
        super(service);
    }

    @Override
    protected Result<?> onSaveOrUpdateBefore(Wiki entity, boolean isSave) {
        if (isSave) {
            Integer recognitionMode = entity.getRecognitionMode();
            Integer type = entity.getType();
            if (EnumWikiType.CONTENT.getCode().equals(type)) {
                Assert.notNull(recognitionMode, "识别模式不能为空");
            }
            if (EnumRecognitionMode.NORMAL.getCode().equals(recognitionMode)) {
                InputStream inputStream = null;
                try {
                    inputStream = storageService.readStream(entity.getFileUrl());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String content = File2TextUtil.readFromStream(inputStream, FilenameUtils.getName(entity.getFileUrl()), null);
                entity.setContent(content);
            }
            if (EnumRecognitionMode.OCR.getCode().equals(recognitionMode)) {
                Assert.notNull(entity.getOcrModelId(), "OCR模型不能为空");
                Model model = modelService.getModelInstance(entity.getOcrModelId());
                OcrModel ocrModel = model.toOcrModel();
                String taskId = ocrModel.runAsync(entity.getFileUrl());
                entity.setTaskId(taskId);
                entity.setTaskStatus(EnumOcrTaskStatus.IN_PROGRESS.getCode());
            }
        }
        return super.onSaveOrUpdateBefore(entity, isSave);
    }

    @Override
    @GetMapping("list")
    public Result<List<Wiki>> list(Wiki entity, Boolean asTree, String sortKey, String sortType) {
        QueryWrapper queryWrapper = QueryWrapper.create(entity, buildOperators(entity));
        queryWrapper.orderBy(buildOrderBy(sortKey, sortType, getDefaultOrderBy()));
        List<Wiki> list = service.list(queryWrapper);
        return Result.ok(Tree.tryToTree(list, "id", "parentId"));
    }

    @Override
    protected Result<?> onRemoveBefore(Collection<Serializable> ids) {

        for (Serializable id : ids) {
            QueryWrapper w = QueryWrapper.create();
            w.eq(Wiki::getParentId, id);
            if (service.exists(w)) {
                throw new BusinessException("请先删除子数据！");
            }
        }

        return super.onRemoveBefore(ids);
    }
}
