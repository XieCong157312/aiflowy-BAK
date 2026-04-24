<script setup lang="ts">
import type { TreeV2Instance } from 'element-plus';

import { nextTick, ref } from 'vue';

import { $t } from '@aiflowy/locales';

import { ElButton, ElDialog, ElMessage, ElTreeV2 } from 'element-plus';

import { api } from '#/api/request';

const props = defineProps({
  title: { type: String, default: '选择 Wiki' },
  width: { type: String, default: '500' },
});

const emit = defineEmits(['getData']);

const dialogVisible = ref(false);
const treeRef = ref<TreeV2Instance>();
const treeData = ref<any[]>([]);
const loading = ref(false);
const selectedIds = ref<(number | string)[]>([]);

const defaultProps = {
  children: 'children',
  label: 'title',
  value: 'id',
};

defineExpose({
  openDialog(defaultSelectedIds: (number | string)[]) {
    selectedIds.value = defaultSelectedIds ? [...defaultSelectedIds] : [];
    dialogVisible.value = true;
    fetchWikiTreeData();
  },
});

// 获取 Wiki 树形数据
const fetchWikiTreeData = async () => {
  loading.value = true;
  try {
    const res = await api.get('/api/v1/wiki/list', {
      params: {
        asTree: true,
      },
    });

    if (res.errorCode === 0) {
      treeData.value = res.data || [];
      // 数据加载完成后设置选中状态（无论是否有选中项都要设置，以清空之前的状态）
      nextTick(() => {
        if (treeRef.value) {
          treeRef.value.setCheckedKeys(selectedIds.value);
        }
      });
    } else {
      ElMessage.error(res.message || $t('message.getDataError'));
    }
  } catch (error) {
    console.error('get wiki tree data error:', error);
    ElMessage.error($t('message.getDataError'));
  } finally {
    loading.value = false;
  }
};

// 处理节点选择变化
const handleCheck = () => {
  // 获取所有选中的节点key
  const checkedKeys = treeRef.value?.getCheckedKeys() || [];
  selectedIds.value = checkedKeys;
};

// 处理确认
const handleSubmit = () => {
  const checkedKeys = treeRef.value?.getCheckedKeys() || [];
  emit('getData', checkedKeys);
  dialogVisible.value = false;
};

// 处理取消
const handleCancel = () => {
  dialogVisible.value = false;
};

// 处理关闭
const handleClose = () => {
  treeData.value = [];
  selectedIds.value = [];
};
</script>

<template>
  <ElDialog
    v-model="dialogVisible"
    :title="props.title"
    :width="props.width"
    draggable
    :close-on-click-modal="false"
    align-center
    @close="handleClose"
  >
    <div class="wiki-tree-container">
      <ElTreeV2
        ref="treeRef"
        :data="treeData"
        :props="defaultProps"
        node-key="id"
        :default-expand-all="true"
        :highlight-current="true"
        show-checkbox
        :check-strictly="true"
        :height="400"
        @check="handleCheck"
        v-loading="loading"
        :element-loading-text="$t('message.loading')"
      >
        <template #default="{ node }">
          <span class="tree-node">
            <span class="node-label">{{ node.label }}</span>
            <span
              v-if="node.children && node.children.length > 0"
              class="node-count"
            >
              ({{ node.children.length }})
            </span>
          </span>
        </template>
      </ElTreeV2>
    </div>
    <template #footer>
      <div class="dialog-footer">
        <ElButton @click="handleCancel">{{ $t('button.cancel') }}</ElButton>
        <ElButton type="primary" @click="handleSubmit">
          {{ $t('button.confirm') }}
        </ElButton>
      </div>
    </template>
  </ElDialog>
</template>

<style scoped>
.wiki-tree-container {
  width: 100%;
  min-height: 400px;
  max-height: 500px;
  padding: 12px;
  background-color: var(--el-bg-color);
  border: 1px solid var(--el-border-color);
  border-radius: 6px;
}

.tree-node {
  display: flex;
  gap: 8px;
  align-items: center;
}

.node-count {
  font-size: 12px;
  color: var(--el-text-color-secondary);
}

.dialog-footer {
  display: flex;
  gap: 12px;
  justify-content: flex-end;
}
</style>
