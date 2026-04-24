package tech.aiflowy.wiki.entity;

import com.mybatisflex.annotation.Table;
import tech.aiflowy.wiki.entity.base.WikiBase;


/**
 * wiki 实体类。
 *
 * @author John
 * @since 2026-04-22
 */
@Table(value = "tb_wiki", comment = "wiki")
public class Wiki extends WikiBase {
}
