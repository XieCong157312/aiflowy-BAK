package tech.aiflowy.wiki.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import tech.aiflowy.wiki.entity.BotWiki;
import tech.aiflowy.wiki.mapper.BotWikiMapper;
import tech.aiflowy.wiki.service.BotWikiService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * bot绑定的wiki 服务层实现。
 *
 * @author John
 * @since 2026-04-23
 */
@Service
public class BotWikiServiceImpl extends ServiceImpl<BotWikiMapper, BotWiki>  implements BotWikiService{

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBotWikiIds(BigInteger botId, List<BigInteger> wikiIds) {
        QueryWrapper delW = QueryWrapper.create();
        delW.eq(BotWiki::getBotId, botId);
        this.remove(delW);
        if (CollectionUtil.isNotEmpty(wikiIds)) {
            wikiIds.stream().distinct().forEach(wikiId -> {
                BotWiki botWiki = new BotWiki();
                botWiki.setBotId(botId);
                botWiki.setWikiId(wikiId);
                this.save(botWiki);
            });
        }
    }
}
