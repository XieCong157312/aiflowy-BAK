package tech.aiflowy.wiki.service;

import com.mybatisflex.core.service.IService;
import tech.aiflowy.wiki.entity.BotWiki;

import java.math.BigInteger;
import java.util.List;

/**
 * bot绑定的wiki 服务层。
 *
 * @author John
 * @since 2026-04-23
 */
public interface BotWikiService extends IService<BotWiki> {

    void updateBotWikiIds(BigInteger botId, List<BigInteger> wikiIds);
}
