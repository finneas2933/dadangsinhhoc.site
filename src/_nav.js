import { cilRectangle, cilPenNib, cilNewspaper, cilEnvelopeClosed, cilAnimal, cilPlant, cilSpreadsheet } from '@coreui/icons'

export default [
  {
    component: 'CNavItem',
    name: 'Dashboard',
    to: '/dashboard',
    icon: 'cil-speedometer',
    badge: {
      color: 'primary',
      text: 'NEW',
    },
  },
  {
    component: 'CNavTitle',
    name: 'Theme',
  },
  {
    component: 'CNavItem',
    name: 'Colors',
    to: '/theme/colors',
    icon: 'cil-drop',
  },
  {
    component: 'CNavItem',
    name: 'Typography',
    to: '/theme/typography',
    icon: 'cil-pencil',
  },
  {
    component: 'CNavTitle',
    name: 'Danh mục quản lý',
  },
  {
    component: 'CNavGroup',
    name: 'Người dùng',
    to: '/nguoi-dung',
    icon: 'cil-people',
    items: [
      {
        component: 'CNavItem',
        name: 'Danh sách',
        to: '/nguoi-dung/danh-sach',
      },
      {
        component: 'CNavItem',
        name: 'Thêm mới',
        to: '/nguoi-dung/them-moi',
      },
      {
        component: 'CNavItem',
        name: 'Thông tin cá nhân',
        to: '/nguoi-dung/thong-tin-ca-nhan',
      },
    ],
  },
  {
    component: 'CNavGroup',
    name: 'Banner',
    to: '/banner',
    icon: cilRectangle,
    items: [
      {
        component: 'CNavItem',
        name: 'Danh sách',
        to: '/banner/danh-sach',
      },
      {
        component: 'CNavItem',
        name: 'Thêm mới',
        to: '/banner/them-moi',
      },
    ],
  },
  {
    component: 'CNavItem',
    name: 'Giới thiệu',
    to: '/gioi-thieu',
    icon: cilPenNib,
  },
  {
    component: 'CNavGroup',
    name: 'Tin tức',
    to: '/tin-tuc',
    icon: cilNewspaper,
    items: [
      {
        component: 'CNavItem',
        name: 'Danh sách',
        to: '/tin-tuc/danh-sach',
      },
      {
        component: 'CNavItem',
        name: 'Thêm mới',
        to: '/tin-tuc/them-moi',
      },
    ],
  },
  {
    component: 'CNavItem',
    name: 'Tin nhắn',
    to: '/tin-nhan',
    icon: cilEnvelopeClosed,
  },
  {
    component: 'CNavGroup',
    name: 'Động vật',
    to: '/dong-vat',
    icon: cilAnimal,
    items: [
      {
        component: 'CNavItem',
        name: 'Ngành',
        to: '/dong-vat/nganh',
      },
      {
        component: 'CNavItem',
        name: 'Lớp',
        to: '/dong-vat/lop',
      },
      {
        component: 'CNavItem',
        name: 'Bộ',
        to: '/dong-vat/bo',
      },
      {
        component: 'CNavItem',
        name: 'Họ',
        to: '/dong-vat/ho',
      },
      {
        component: 'CNavItem',
        name: 'Loài',
        to: '/dong-vat/loai',
      },
    ],
  },
  {
    component: 'CNavGroup',
    name: 'Thực vật',
    to: '/thuc-vat',
    icon: cilPlant,
    items: [
      {
        component: 'CNavItem',
        name: 'Ngành',
        to: '/thuc-vat/nganh',
      },
      {
        component: 'CNavItem',
        name: 'Lớp',
        to: '/thuc-vat/lop',
      },
      {
        component: 'CNavItem',
        name: 'Bộ',
        to: '/thuc-vat/bo',
      },
      {
        component: 'CNavItem',
        name: 'Họ',
        to: '/thuc-vat/ho',
      },
      {
        component: 'CNavItem',
        name: 'Loài',
        to: '/thuc-vat/loai',
      },
    ],
  },
  {
    component: 'CNavItem',
    name: 'Thống kê',
    to: '/thong-ke',
    icon: cilSpreadsheet,
  },
  {
    component: 'CNavGroup',
    name: 'Base',
    to: '/base',
    icon: 'cil-puzzle',
    items: [
      {
        component: 'CNavItem',
        name: 'Accordion',
        to: '/base/accordion',
      },
      {
        component: 'CNavItem',
        name: 'Breadcrumbs',
        to: '/base/breadcrumbs',
      },
      {
        component: 'CNavItem',
        name: 'Cards',
        to: '/base/cards',
      },
      {
        component: 'CNavItem',
        name: 'Carousels',
        to: '/base/carousels',
      },
      {
        component: 'CNavItem',
        name: 'Collapses',
        to: '/base/collapses',
      },
      {
        component: 'CNavItem',
        name: 'List Groups',
        to: '/base/list-groups',
      },
      {
        component: 'CNavItem',
        name: 'Navs & Tabs',
        to: '/base/navs',
      },
      {
        component: 'CNavItem',
        name: 'Paginations',
        to: '/base/paginations',
      },
      {
        component: 'CNavItem',
        name: 'Placeholders',
        to: '/base/placeholders',
      },
      {
        component: 'CNavItem',
        name: 'Popovers',
        to: '/base/popovers',
      },
      {
        component: 'CNavItem',
        name: 'Progress',
        to: '/base/progress',
      },
      {
        component: 'CNavItem',
        name: 'Spinners',
        to: '/base/spinners',
      },
      {
        component: 'CNavItem',
        name: 'Tables',
        to: '/base/tables',
      },
      {
        component: 'CNavItem',
        name: 'Tabs',
        to: '/base/tabs',
      },
      {
        component: 'CNavItem',
        name: 'Tooltips',
        to: '/base/tooltips',
      },
    ],
  },
  {
    component: 'CNavGroup',
    name: 'Buttons',
    to: '/buttons',
    icon: 'cil-cursor',
    items: [
      {
        component: 'CNavItem',
        name: 'Buttons',
        to: '/buttons/standard-buttons',
      },
      {
        component: 'CNavItem',
        name: 'Button Groups',
        to: '/buttons/button-groups',
      },
      {
        component: 'CNavItem',
        name: 'Dropdowns',
        to: '/buttons/dropdowns',
      },
    ],
  },
  {
    component: 'CNavGroup',
    name: 'Forms',
    to: '/forms',
    icon: 'cil-notes',
    items: [
      {
        component: 'CNavItem',
        name: 'Form Control',
        to: '/forms/form-control',
      },
      {
        component: 'CNavItem',
        name: 'Select',
        to: '/forms/select',
      },
      {
        component: 'CNavItem',
        name: 'Checks & Radios',
        to: '/forms/checks-radios',
      },
      {
        component: 'CNavItem',
        name: 'Range',
        to: '/forms/range',
      },
      {
        component: 'CNavItem',
        name: 'Input Group',
        to: '/forms/input-group',
      },
      {
        component: 'CNavItem',
        name: 'Floating Labels',
        to: '/forms/floating-labels',
      },
      {
        component: 'CNavItem',
        name: 'Layout',
        to: '/forms/layout',
      },
      {
        component: 'CNavItem',
        name: 'Validation',
        to: '/forms/validation',
      },
    ],
  },
  {
    component: 'CNavItem',
    name: 'Charts',
    to: '/charts',
    icon: 'cil-chart-pie',
  },
  {
    component: 'CNavGroup',
    name: 'Icons',
    to: '/icons',
    icon: 'cil-star',
    items: [
      {
        component: 'CNavItem',
        name: 'CoreUI Icons',
        to: '/icons/coreui-icons',
        badge: {
          color: 'info',
          text: 'NEW',
        },
      },
      {
        component: 'CNavItem',
        name: 'Brands',
        to: '/icons/brands',
      },
      {
        component: 'CNavItem',
        name: 'Flags',
        to: '/icons/flags',
      },
    ],
  },
  {
    component: 'CNavGroup',
    name: 'Notifications',
    to: '/notifications',
    icon: 'cil-bell',
    items: [
      {
        component: 'CNavItem',
        name: 'Alerts',
        to: '/notifications/alerts',
      },
      {
        component: 'CNavItem',
        name: 'Badges',
        to: '/notifications/badges',
      },
      {
        component: 'CNavItem',
        name: 'Modals',
        to: '/notifications/modals',
      },
      {
        component: 'CNavItem',
        name: 'Toasts',
        to: '/notifications/toasts',
      },
    ],
  },
  {
    component: 'CNavItem',
    name: 'Widgets',
    to: '/widgets',
    icon: 'cil-calculator',
    badge: {
      color: 'primary',
      text: 'NEW',
      shape: 'pill',
    },
  },
  {
    component: 'CNavTitle',
    name: 'Extras',
  },
  {
    component: 'CNavGroup',
    name: 'Pages',
    to: '/pages',
    icon: 'cil-star',
    items: [
      {
        component: 'CNavItem',
        name: 'Login',
        to: '/pages/login',
      },
      {
        component: 'CNavItem',
        name: 'Register',
        to: '/pages/register',
      },
      {
        component: 'CNavItem',
        name: 'Error 404',
        to: '/pages/404',
      },
      {
        component: 'CNavItem',
        name: 'Error 500',
        to: '/pages/500',
      },
    ],
  },
]
