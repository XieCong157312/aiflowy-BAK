package tech.aiflowy.wiki.agentsflex;

import com.agentsflex.wiki.Wiki;
import com.agentsflex.wiki.WikiProvider;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import tech.aiflowy.wiki.service.WikiService;

@Component
public class AIFlowyWikiProvider implements WikiProvider {

    @Resource
    private WikiService wikiService;

    @Override
    public Wiki getWiki(String s) {

        tech.aiflowy.wiki.entity.Wiki record = wikiService.getById(s);
        if (record != null) {
            Wiki afWiki = new Wiki();
            afWiki.setPath(record.getId().toString());
            afWiki.setTitle(record.getTitle());
            afWiki.setSummary(record.getDescription());
            afWiki.setContent(record.getContent());
            return afWiki;
        }
        return null;

    }
}
