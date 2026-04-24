package tech.aiflowy.wiki.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import tech.aiflowy.wiki.entity.Wiki;
import tech.aiflowy.wiki.mapper.WikiMapper;
import tech.aiflowy.wiki.service.WikiService;
import org.springframework.stereotype.Service;

/**
 * wiki 服务层实现。
 *
 * @author John
 * @since 2026-04-22
 */
@Service
public class WikiServiceImpl extends ServiceImpl<WikiMapper, Wiki>  implements WikiService{

}
