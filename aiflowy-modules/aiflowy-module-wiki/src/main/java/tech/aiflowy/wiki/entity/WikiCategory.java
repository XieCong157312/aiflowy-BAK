package tech.aiflowy.wiki.entity;

import com.mybatisflex.annotation.Table;
import tech.aiflowy.wiki.entity.base.WikiCategoryBase;


/**
 * wiki分类 实体类。
 *
 * @author John
 * @since 2026-04-22
 */
@Table(value = "tb_wiki_category", comment = "wiki分类")
public class WikiCategory extends WikiCategoryBase {
}
