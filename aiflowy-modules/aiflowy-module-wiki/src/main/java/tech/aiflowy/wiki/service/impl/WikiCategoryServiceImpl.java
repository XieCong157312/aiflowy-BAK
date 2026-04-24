package tech.aiflowy.wiki.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import tech.aiflowy.wiki.entity.WikiCategory;
import tech.aiflowy.wiki.mapper.WikiCategoryMapper;
import tech.aiflowy.wiki.service.WikiCategoryService;
import org.springframework.stereotype.Service;

/**
 * wiki分类 服务层实现。
 *
 * @author John
 * @since 2026-04-22
 */
@Service
public class WikiCategoryServiceImpl extends ServiceImpl<WikiCategoryMapper, WikiCategory>  implements WikiCategoryService{

}
