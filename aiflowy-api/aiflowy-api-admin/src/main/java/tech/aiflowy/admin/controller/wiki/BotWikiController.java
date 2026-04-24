package tech.aiflowy.admin.controller.wiki;

import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aiflowy.common.annotation.UsePermission;
import tech.aiflowy.common.domain.Result;
import tech.aiflowy.common.tree.Tree;
import tech.aiflowy.common.web.controller.BaseCurdController;
import tech.aiflowy.wiki.entity.BotWiki;
import tech.aiflowy.wiki.service.BotWikiService;

import java.util.List;

@RequestMapping("/api/v1/botWiki")
@RestController
@UsePermission(moduleName = "/api/v1/wiki")
public class BotWikiController extends BaseCurdController<BotWikiService, BotWiki> {

    public BotWikiController(BotWikiService service) {
        super(service);
    }

    @Override
    public Result<List<BotWiki>> list(BotWiki entity, Boolean asTree, String sortKey, String sortType) {
        QueryWrapper queryWrapper = QueryWrapper.create(entity, buildOperators(entity));
        queryWrapper.orderBy(buildOrderBy(sortKey, sortType, getDefaultOrderBy()));
        List<BotWiki> list = Tree.tryToTree(service.getMapper().selectListWithRelationsByQuery(queryWrapper), asTree);
        return Result.ok(list);
    }

    @PostMapping("/updateBotWikiIds")
    public Result<Void> updateBotWikiIds(@RequestBody BotWiki request) {
        service.updateBotWikiIds(request.getBotId(), request.getWikiIds());
        return Result.ok();
    }

}
