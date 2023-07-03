import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
 * hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
 *                                if not set alwaysShow, only more than one route under the children
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
 **/
export const constantRouterMap = [{
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path:'/userquestions',
    hidden: true,
    component: () => import('@/views/userquestions/index'),
  },
  {
    path:'/messagePage',
    hidden: true,
    component: () => import('@/views/messagePage/index'),
  },
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: '/home',
    children: [{
      path: 'home',
      name: 'home',
      component: () => import('@/views/home/index'),
      meta: {
        title: '数据驾驶舱',
        icon: 'home'
      }
    }]
  }
]

export const asyncRouterMap = [
  {
    path: '/ums',
    component: Layout,
    redirect: '/ums/admin',
    name: 'ums',
    meta: {
      title: '权限',
      icon: 'ums'
    },
    children: [{
        path: 'admin',
        name: 'admin',
        component: () => import('@/views/ums/admin/index'),
        meta: {
          title: '人员管理',
          icon: 'ums-admin'
        }
      },
      {
        path: 'role',
        name: 'role',
        component: () => import('@/views/ums/role/index'),
        meta: {
          title: '角色列表',
          icon: 'ums-role'
        }
      },
      {
        path: 'allocMenu',
        name: 'allocMenu',
        component: () => import('@/views/ums/role/allocMenu'),
        meta: {
          title: '分配菜单'
        },
        hidden: true
      },
      {
        path: 'allocResource',
        name: 'allocResource',
        component: () => import('@/views/ums/role/allocResource'),
        meta: {
          title: '分配资源'
        },
        hidden: true
      },
      {
        path: 'menu',
        name: 'menu',
        component: () => import('@/views/ums/menu/index'),
        meta: {
          title: '菜单列表',
          icon: 'ums-menu'
        }
      },
      {
        path: 'addMenu',
        name: 'addMenu',
        component: () => import('@/views/ums/menu/add'),
        meta: {
          title: '添加菜单'
        },
        hidden: true
      },
      {
        path: 'updateMenu',
        name: 'updateMenu',
        component: () => import('@/views/ums/menu/update'),
        meta: {
          title: '修改菜单'
        },
        hidden: true
      },
    ]
  },
  {
    path:'/ums/member',
    component: Layout,
    redirect: '/ums/member/memberList',
    name: 'member',
    meta: {title: '客户管理', icon: 'ums-admin'},
    children: [
      {
        path: 'memberList',
        name: 'memberList',
        component: () => import('@/views/ums/member/memberList'),
        meta: {title: '客户列表', icon: 'ums-admin'}
      },
      {
        path: 'memberInformation',
        name: 'memberInformation',
        component: () => import('@/views/ums/member/memberInformation'),
        meta: {title: '用户信息', icon: 'ums-admin'},
        hidden: true
      },
      {
        path: 'memberDetail',
        name: 'memberDetail',
        component: () => import('@/views/ums/member/memberDetail'),
        meta: {title: '查看'},
        hidden: true
      }
    ]
  },
  {
    path:'/workorder',
    component: Layout,
    redirect: '/workorder/index',
    name: 'workorder',
    meta: {title: '工单中心', icon: 'workorder'},
    children: [
      {
        path: 'workOrderList',
        name: 'workOrderList',
        component: () => import('@/views/workorder/index'),
        meta: {title: '工单中心', icon: 'workorder'}
      }
    ]
  },
  {
    path:'/department',
    component: Layout,
    redirect: '/department/index',
    name: 'department',
    meta: {title: '服务点管理', icon: 'department'},
    children: [
      {
        path: 'deptList',
        name: 'deptList',
        component: () => import('@/views/department/index'),
        meta: {title: '服务点管理', icon: 'department'}
      }
    ]
  },
  {
    path:'/conversation',
    component: Layout,
    redirect: '/conversation/callLog',
    name: 'conversation',
    meta: {title: '呼叫中心', icon: 'callManage'},
    children: [
      {
        path: 'callLog',
        name: 'callLog',
        component: () => import('@/views/conversation/callLog'),
        meta: {title: '呼叫中心', icon: 'callLog'}
      },
      {
        path: 'conversationInfo',
        name: 'conversationInfo',
        component: () => import('@/views/conversation/index'),
        meta: {title: '会话管理', icon: 'conversationInfo'},
        hidden: true
      }
    ]
  },
  {
    path:'/tags',
    component: Layout,
    redirect: '/tags/tagList',
    name: 'tags',
    meta: {title: '标签管理', icon: 'tag'},
    children: [
      {
        path: 'tagList',
        name: 'tagList',
        component: () => import('@/views/tags/index'),
        meta: {title: '标签管理', icon: 'tag'}
      }
    ]
  },
  {
    path:'/questionnaire',
    component: Layout,
    redirect: '/questionnaire/index',
    name: 'questionnaire',
    meta: {title: '问卷管理', icon: 'questionnaire'},
    children: [
      {
        path: 'questionnaire',
        name: 'questionnaire',
        component: () => import('@/views/questionnaire/index'),
        meta: {title: '问卷管理', icon: 'questionnaire'}
      },{
        path: 'questions',
        name: 'questions',
        component: () => import('@/views/questionnaire/questions'),
        meta: {title: '问卷内容', icon: 'questions'},
        hidden: true
      },{
        path: 'answers',
        name: 'answers',
        component: () => import('@/views/questionnaire/answers'),
        meta: {title: '回答内容', icon: 'answers'},
        hidden: true
      },
    ]
  },
  {
    path:'/groupCall',
    component: Layout,
    redirect: '/groupCall/groupCall',
    name: 'groupCall',
    meta: {title: '群呼管理', icon: 'tag'},
    children: [
      {
        path: 'groupCall',
        name: 'groupCall',
        component: () => import('@/views/groupCall/index'),
        meta: {title: '群呼管理', icon: 'groupCall'}
      }
    ]
  },
  {
    path:'/archives',
    component: Layout,
    redirect: '/archives/archivesList',
    name: 'archives',
    meta: {title: '客户档案', icon: 'archives'},
    children: [
      {
        path: 'archivesList',
        name: 'archivesList',
        component: () => import('@/views/archives/index'),
        meta: {title: '客户档案', icon: 'archives'}
      },{
        path: 'archivesDetail',
        name: 'archivesDetail',
        component: () => import('@/views/archives/detail'),
        meta: {title: '档案详情', icon: 'archives'},
        hidden: true
      }
    ]
  },
  {
    path:'/knowledge',
    component: Layout,
    redirect: '/knowledge/knowledgeList',
    name: 'knowledgeList',
    meta: {title: '知识库管理', icon: 'knowledge'},
    children: [
      {
        path: 'knowledgeList',
        name: 'knowledgeList',
        component: () => import('@/views/knowledge/index'),
        meta: {title: '知识库管理', icon: 'knowledge'}
      }
    ]
  },
  {
    path:'/enterprise',
    component: Layout,
    redirect: '/enterprise/index',
    name: 'enterprise',
    meta: {title: '系统管理', icon: 'enterprise'},
    children: [
      {
        path: 'enterpriseList',
        name: 'enterpriseList',
        component: () => import('@/views/enterprise/index'),
        meta: {title: '系统管理', icon: 'enterprise'}
      }
    ]
  },
  {
    path:'/dataScreen',
    component: Layout,
    name: 'dataScreen',
    hidden:true,
    component: () => import('@/views/dataScreen/index'),
  },
  {
    path: '*',
    redirect: '/404',
    hidden: true
  }


  ,
  // {
  //   path: '/ads',
  //   component: Layout,
  //   redirect: '/ads/problem',
  //   name: 'ads',
  //   meta: {
  //     title: '客户服务',
  //     icon: 'ums-admin'
  //   },
  //   children: [{
  //       path: 'problem',
  //       name: 'problem',
  //       component: () => import('@/views/ads/problem/index'),
  //       meta: {
  //         title: '常见问题',
  //         icon: 'ums-admin'
  //       }
  //     },
  //     // {
  //     //   path: 'information',
  //     //   name: 'information',
  //     //   component: () => import('@/views/ads/information/index'),
  //     //   meta: {
  //     //     title: '添加常见问题',
  //     //     icon: 'ums-admin'
  //     //   }
  //     // },
  //   ]
  // },
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({
    y: 0
  }),
  routes: constantRouterMap
})
