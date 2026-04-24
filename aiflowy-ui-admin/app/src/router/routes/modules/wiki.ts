import type { RouteRecordRaw } from 'vue-router';

import { $t } from '#/locales';

const routes: RouteRecordRaw[] = [
  {
    meta: {
      title: $t('wiki.detail'),
      hideInMenu: true,
      hideInTab: true,
      hideInBreadcrumb: true,
      fullPathKey: true,
      activePath: '/wiki',
    },
    name: 'WikiDetail',
    path: '/wiki/detail',
    component: () => import('#/views/wiki/WikiDetail.vue'),
  },
];

export default routes;
