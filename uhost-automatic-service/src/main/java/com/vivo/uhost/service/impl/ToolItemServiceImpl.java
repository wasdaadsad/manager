package com.vivo.uhost.service.impl;


import com.vivo.uhost.core.domain.bo.ToolItemBO;
import com.vivo.uhost.core.util.CfgContants;
import com.vivo.uhost.dal.dao.IToolItemDao;
import com.vivo.uhost.dal.entity.ToolItem;
import com.vivo.uhost.dal.entity.process.TestItem;
import com.vivo.uhost.service.IToolItemService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author litingfa  2018/3/27 17:14
 * @version 1.0
 * @description
 */
@Service
public class ToolItemServiceImpl implements IToolItemService {

    @Autowired
    private IToolItemDao toolItemDao;

    @Override
    public void saveTestItem4Tool(List<ToolItemBO> toolItemBOs, Long toolId) {
        List<ToolItemBO> res = cleanEmptyList(toolItemBOs);
        Collections.sort(res, new Comparator<ToolItemBO>() {
            @Override
            public int compare(ToolItemBO o1, ToolItemBO o2) {
                if(CfgContants.TOOL_STATE_VALID.equals(o1.getIsDefault()) && !CfgContants.TOOL_STATE_VALID.equals(o2.getIsDefault())){
                    return -1;
                }else if(CfgContants.TOOL_STATE_VALID.equals(o2.getIsDefault()) && !CfgContants.TOOL_STATE_VALID.equals(o1.getIsDefault())){
                    return 1;
                }
                if(CfgContants.TOOL_STATE_VALID.equals(o1.getIsLast()) && !CfgContants.TOOL_STATE_VALID.equals(o2.getIsLast())){
                    return 1;
                }else if(CfgContants.TOOL_STATE_VALID.equals(o2.getIsLast()) && !CfgContants.TOOL_STATE_VALID.equals(o1.getIsLast())){
                    return -1;
                }
                return 0;
            }
        });
        List<ToolItem> toolItems = new ArrayList<>();
        for (ToolItemBO re : res) {
            ToolItem toolItem = re.toEntity();
            toolItems.add(toolItem);
        }
        removeDeletedTestItem(toolItems, toolId);

        Set<Integer> typeSet = new HashSet<Integer>();
        int order = 1;
        for (ToolItem testItem :toolItems){
            if (testItem.getTestNum() == null || StringUtils.isBlank(testItem.getTestName())
                    || typeSet.contains(testItem.getTestNum())){
                continue;
            }
            if (CfgContants.TOOL_STATE_VALID.equals(testItem.getIsDefault())){
                if (testItem.getTestDuration() == null || testItem.getTestDuration() <= 0){
                    continue;
                }
                testItem.setTestDuration(testItem.getTestDuration() * 60 * 60);  //小时转换为秒
            }
            testItem.setToolId(toolId);
            testItem.setItemOrder(order++);
            testItem.setState(CfgContants.STATE_VALID);
            if (testItem.getId() == null){
                toolItemDao.insertTestItem(testItem);
            }else {
                toolItemDao.updateItem(testItem);
            }
            typeSet.add(testItem.getTestNum());
        }

    }

    @Override
    public List<ToolItemBO> findByTid(Long toolId) {
        List<ToolItem> toolItems = toolItemDao.findByTid(toolId);
        List<ToolItemBO> toolItemBOS = new ArrayList<>();
        for (ToolItem toolItem : toolItems) {
            ToolItemBO toolItemBO = new ToolItemBO(toolItem);
            toolItemBOS.add(toolItemBO);
        }
        return toolItemBOS;
    }

    @Override
    public List<TestItem> getListByToolId(Integer toolId) {
        List<ToolItem> toolItems = toolItemDao.findByTid(Long.valueOf(toolId));
        List<TestItem> testItems = new ArrayList<>();
        for (ToolItem toolItem : toolItems) {
            TestItem testItem = new TestItem(toolItem);
            testItems.add(testItem);
        }
        return testItems;
    }

    @Override
    public List<TestItem> getListByTaskId(Integer taskId) {
        List<ToolItem> toolItems = toolItemDao.getByTaskId(taskId);
        List<TestItem> testItems = new ArrayList<>();
        for (ToolItem toolItem : toolItems) {
            TestItem testItem = new TestItem(toolItem);
            testItems.add(testItem);
        }
        return testItems;
    }


    /**
     * 删除已存在但update时删除了的toolitem
     * @param toolItems
     * @param toolId
     */
    private void removeDeletedTestItem(List<ToolItem> toolItems, Long toolId) {
        List<ToolItem> itemsBefore = toolItemDao.findByTid(toolId);
        boolean isDeleted = true;
        if (CollectionUtils.isNotEmpty(itemsBefore)){
            for (ToolItem testItemBefore: itemsBefore){
                for (ToolItem itemNow: toolItems){
                    if (itemNow.getId() != null && itemNow.getId().intValue() == testItemBefore.getId().intValue()){
                        isDeleted = false;
                        break;
                    } else{
                        isDeleted = true;
                    }
                }
                if (isDeleted){
                    toolItemDao.deleteById(testItemBefore.getId());
                }
            }
        }
    }

    /**
     * 去除List中没有意思的数据
     * @param toolItemBOs
     * @return
     */
    private List<ToolItemBO> cleanEmptyList(List<ToolItemBO> toolItemBOs) {
        List<ToolItemBO> res = new ArrayList<>();
        for (ToolItemBO toolItemBO : toolItemBOs) {
            if (StringUtils.isBlank(toolItemBO.getCaseName()) || StringUtils.isBlank(toolItemBO.getTestName()) || toolItemBO.getTestNum() == null){
                continue;
            }else{
                res.add(toolItemBO);
            }
        }
        return res;
    }
}
