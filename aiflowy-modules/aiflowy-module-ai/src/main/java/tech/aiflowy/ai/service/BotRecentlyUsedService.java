package tech.aiflowy.ai.service;

import com.mybatisflex.core.service.IService;
import tech.aiflowy.ai.entity.BotRecentlyUsed;

import java.math.BigInteger;
import java.util.List;

/**
 * 最近使用 服务层。
 *
 * @author ArkLight
 * @since 2025-12-18
 */
public interface BotRecentlyUsedService extends IService<BotRecentlyUsed> {

    void removeRecentlyAllUsed(List<BigInteger> botIds);

}
