package tech.aiflowy.wiki.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.RelationOneToOne;
import com.mybatisflex.annotation.Table;
import tech.aiflowy.wiki.entity.base.BotWikiBase;

import java.math.BigInteger;
import java.util.List;


/**
 * bot绑定的wiki 实体类。
 *
 * @author John
 * @since 2026-04-23
 */
@Table(value = "tb_bot_wiki", comment = "bot绑定的wiki")
public class BotWiki extends BotWikiBase {

    @Column(ignore = true)
    private List<BigInteger> wikiIds;

    @RelationOneToOne(selfField = "wikiId", targetField = "id")
    private Wiki wiki;

    public List<BigInteger> getWikiIds() {
        return wikiIds;
    }

    public void setWikiIds(List<BigInteger> wikiIds) {
        this.wikiIds = wikiIds;
    }

    public Wiki getWiki() {
        return wiki;
    }

    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }
}
