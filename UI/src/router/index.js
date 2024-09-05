import { h, resolveComponent } from 'vue'
import { createRouter, createWebHashHistory } from 'vue-router'

import DefaultLayout from '@/layouts/DefaultLayout'
import Login from '../views/pages/Login.vue'

const routes = [
  {
    path: '/',
    redirect: '/login', // Redirect đến trang login khi ứng dụng khởi động
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: DefaultLayout,
    //meta: { requiresAuth: true },  // Cần đăng nhập để truy cập
    children: [
      {
        path: '/dashboard',
        name: 'Dashboard',
        component: () => import('@/views/dashboard/Dashboard1.vue'),
      },
      {
        path: '/theme',
        name: 'Theme',
        redirect: '/theme/typography',
      },
      {
        path: '/theme/colors',
        name: 'Colors',
        component: () => import('@/views/theme/Colors.vue'),
      },
      {
        path: '/theme/typography',
        name: 'Typography',
        component: () => import('@/views/theme/Typography.vue'),
      },
      {
        path: '/nguoi-dung',
        name: 'Người dùng',
        component: {
          render() {
            return h(resolveComponent('router-view'))
          },
        },
        redirect: '/nguoi-dung/danh-sach',
        children: [
          {
            path: '/nguoi-dung/danh-sach',
            name: 'Danh sách người dùng',
            component: () => import('@/views/nguoidung/DanhSachNguoiDung.vue'),
          },
          {
            path: '/nguoi-dung/chi-tiet',
            name: 'Chi tiết người dùng',
            component: () => import('@/views/nguoidung/ChiTietNguoiDung.vue'),
          },
          {
            path: '/nguoi-dung/them-moi',
            name: 'Thêm mới người dùng',
            component: () => import('@/views/nguoidung/ThemMoiNguoiDung.vue'),
          },
          {
            // path: '/nguoi-dung/chinh-sua/:id',
            path: '/nguoi-dung/chinh-sua',
            name: 'Chỉnh sửa người dùng',
            component: () => import('@/views/nguoidung/ChinhSuaNguoiDung.vue'),
          },
          {
            path: '/nguoi-dung/thong-tin-ca-nhan',
            name: 'Thông tin cá nhân',
            component: () => import('@/views/nguoidung/ThongTinCaNhan.vue'),
          },
          {
            path: '/nguoi-dung/cap-nhat-thong-tin-ca-nhan',
            name: 'Cập nhật thông tin cá nhân',
            component: () => import('@/views/nguoidung/CapNhatThongTinCaNhan.vue'),
          },
          {
            path: '/nguoi-dung/doi-mat-khau',
            name: 'Đổi mật khẩu',
            component: () => import('@/views/nguoidung/DoiMatKhau.vue'),
          },
        ],
      },
      {
        path: '/banner',
        name: 'Banner',
        component: {
          render() {
            return h(resolveComponent('router-view'))
          },
        },
        redirect: '/banner/danh-sach',
        children: [
          {
            path: '/banner/danh-sach',
            name: 'Danh sách banner',
            component: () => import('@/views/banner/DanhSachBanner.vue'),
          },
          {
            path: '/banner/them-moi',
            name: 'Thêm mới banner',
            component: () => import('@/views/banner/ThemMoiBanner.vue'),
          },
        ],
      },
      {
        path: '/gioi-thieu',
        name: 'Giới thiệu',
        component: {
          render() {
            return h(resolveComponent('router-view'))
          },
        },
        redirect: '/gioi-thieu',
        children: [
          {
            path: '/gioi-thieu',
            name: 'Danh sách giới thiệu',
            component: () => import('@/views/gioithieu/DanhSachGioiThieu.vue'),
          },
          {
            // path: '/nguoi-dung/chinh-sua/:id',
            path: '/gioi-thieu/chinh-sua',
            name: 'Chỉnh sửa giới thiệu',
            component: () => import('@/views/gioithieu/ChinhSuaGioiThieu.vue'),
          },
        ],
      },
      {
        path: '/tin-tuc',
        name: 'Tin tức',
        component: {
          render() {
            return h(resolveComponent('router-view'))
          },
        },
        redirect: '/tin-tuc/danh-sach',
        children: [
          {
            path: '/tin-tuc/danh-sach',
            name: 'Danh sách tin tức',
            component: () => import('@/views/tintuc/DanhSachTinTuc.vue'),
          },
          {
            path: '/tin-tuc/them-moi',
            name: 'Thêm mới tin tức',
            component: () => import('@/views/tintuc/ThemMoiTinTuc.vue'),
          },
          {
            path: '/tin-tuc/chinh-sua',
            name: 'Chỉnh sửa tin tức',
            component: () => import('@/views/tintuc/ChinhSuaTinTuc.vue'),
          },
        ],
      },
      {
        path: '/tin-nhan',
        name: 'Tin nhắn',
        component: () => import('@/views/tinnhan/DanhSachTinNhan.vue'),
      },
      {
        path: '/dong-vat',
        name: 'Động vật',
        component: {
          render() {
            return h(resolveComponent('router-view'))
          },
        },
        redirect: '/dong-vat/nganh',
        children: [
          {
            path: '/dong-vat/nganh',
            name: 'Ngành động vật',
            component: () => import('@/views/dongthucvat/nganh/DanhSachNganh.vue'),
          },
          {
            path: '/dong-vat/lop',
            name: 'Lớp động vật',
            component: () => import('@/views/dongthucvat/lop/DanhSachLop.vue'),
          },
          {
            path: '/dong-vat/bo',
            name: 'Bộ động vật',
            component: () => import('@/views/dongthucvat/bo/DanhSachBo.vue'),
          },
          {
            path: '/dong-vat/ho',
            name: 'Họ động vật',
            component: () => import('@/views/dongthucvat/ho/DanhSachHo.vue'),
          },
          {
            path: '/dong-vat/loai',
            name: 'Loài động vật',
            component: () => import('@/views/dongthucvat/loai/DanhSachLoai.vue'),
          },
          {
            path: '/dong-vat/loai/them-moi',
            name: 'Thêm mới loài động vật',
            component: () => import('@/views/dongthucvat/loai/ThemMoiLoai.vue'),
          },
          {
            // path: '/dong-vat/loai/chinh-sua/:id', // :id là tham số động để nhận ID loài
            path: '/dong-vat/loai/chinh-sua',
            name: 'Chỉnh sửa loài động vật',
            component: () => import('@/views/dongthucvat/loai/ChinhSuaLoai.vue'),
          },
        ],
      },
      {
        path: '/thuc-vat',
        name: 'Thực vật',
        component: {
          render() {
            return h(resolveComponent('router-view'))
          },
        },
        redirect: '/thuc-vat/nganh',
        children: [
          {
            path: '/thuc-vat/nganh',
            name: 'Ngành thực vật',
            component: () => import('@/views/dongthucvat/nganh/DanhSachNganh.vue'),
          },
          {
            path: '/thuc-vat/lop',
            name: 'Lớp thực vật',
            component: () => import('@/views/dongthucvat/lop/DanhSachLop.vue'),
          },
          {
            path: '/thuc-vat/bo',
            name: 'Bộ thực vật',
            component: () => import('@/views/dongthucvat/bo/DanhSachBo.vue'),
          },
          {
            path: '/thuc-vat/ho',
            name: 'Họ thực vật',
            component: () => import('@/views/dongthucvat/ho/DanhSachHo.vue'),
          },
          {
            path: '/thuc-vat/loai',
            name: 'Loài thực vật',
            component: () => import('@/views/dongthucvat/loai/DanhSachLoai.vue'),
          },
          {
            path: '/thuc-vat/loai/them-moi',
            name: 'Thêm mới loài thực vật',
            component: () => import('@/views/dongthucvat/loai/ThemMoiLoai.vue'),
          },
          {
            // path: '/thuc-vat/loai/chinh-sua/:id', // :id là tham số động để nhận ID loài
            path: '/thuc-vat/loai/chinh-sua',
            name: 'Chỉnh sửa loài thực vật',
            component: () => import('@/views/dongthucvat/loai/ChinhSuaLoai.vue'),
          },
        ],
      },
      {
        path: '/thong-ke',
        name: 'Thống kê',
        component: () => import('@/views/thongke/ThongKe.vue'),
      },
      {
        path: '/base',
        name: 'Base',
        component: {
          render() {
            return h(resolveComponent('router-view'))
          },
        },
        redirect: '/base/breadcrumbs',
        children: [
          {
            path: '/base/accordion',
            name: 'Accordion',
            component: () => import('@/views/base/Accordion.vue'),
          },
          {
            path: '/base/breadcrumbs',
            name: 'Breadcrumbs',
            component: () => import('@/views/base/Breadcrumbs.vue'),
          },
          {
            path: '/base/cards',
            name: 'Cards',
            component: () => import('@/views/base/Cards.vue'),
          },
          {
            path: '/base/carousels',
            name: 'Carousels',
            component: () => import('@/views/base/Carousels.vue'),
          },
          {
            path: '/base/collapses',
            name: 'Collapses',
            component: () => import('@/views/base/Collapses.vue'),
          },
          {
            path: '/base/list-groups',
            name: 'List Groups',
            component: () => import('@/views/base/ListGroups.vue'),
          },
          {
            path: '/base/navs',
            name: 'Navs',
            component: () => import('@/views/base/Navs.vue'),
          },
          {
            path: '/base/paginations',
            name: 'Paginations',
            component: () => import('@/views/base/Paginations.vue'),
          },
          {
            path: '/base/placeholders',
            name: 'Placeholders',
            component: () => import('@/views/base/Placeholders.vue'),
          },
          {
            path: '/base/popovers',
            name: 'Popovers',
            component: () => import('@/views/base/Popovers.vue'),
          },
          {
            path: '/base/progress',
            name: 'Progress',
            component: () => import('@/views/base/Progress.vue'),
          },
          {
            path: '/base/spinners',
            name: 'Spinners',
            component: () => import('@/views/base/Spinners.vue'),
          },
          {
            path: '/base/tables',
            name: 'Tables',
            component: () => import('@/views/base/Tables.vue'),
          },
          {
            path: '/base/tabs',
            name: 'Tabs',
            component: () => import('@/views/base/Tabs.vue'),
          },
          {
            path: '/base/tooltips',
            name: 'Tooltips',
            component: () => import('@/views/base/Tooltips.vue'),
          },
        ],
      },
      {
        path: '/buttons',
        name: 'Buttons',
        component: {
          render() {
            return h(resolveComponent('router-view'))
          },
        },
        redirect: '/buttons/standard-buttons',
        children: [
          {
            path: '/buttons/standard-buttons',
            name: 'Buttons',
            component: () => import('@/views/buttons/Buttons.vue'),
          },
          {
            path: '/buttons/dropdowns',
            name: 'Dropdowns',
            component: () => import('@/views/buttons/Dropdowns.vue'),
          },
          {
            path: '/buttons/button-groups',
            name: 'Button Groups',
            component: () => import('@/views/buttons/ButtonGroups.vue'),
          },
        ],
      },
      {
        path: '/forms',
        name: 'Forms',
        component: {
          render() {
            return h(resolveComponent('router-view'))
          },
        },
        redirect: '/forms/form-control',
        children: [
          {
            path: '/forms/form-control',
            name: 'Form Control',
            component: () => import('@/views/forms/FormControl.vue'),
          },
          {
            path: '/forms/select',
            name: 'Select',
            component: () => import('@/views/forms/Select.vue'),
          },
          {
            path: '/forms/checks-radios',
            name: 'Checks & Radios',
            component: () => import('@/views/forms/ChecksRadios.vue'),
          },
          {
            path: '/forms/range',
            name: 'Range',
            component: () => import('@/views/forms/Range.vue'),
          },
          {
            path: '/forms/input-group',
            name: 'Input Group',
            component: () => import('@/views/forms/InputGroup.vue'),
          },
          {
            path: '/forms/floating-labels',
            name: 'Floating Labels',
            component: () => import('@/views/forms/FloatingLabels.vue'),
          },
          {
            path: '/forms/layout',
            name: 'Layout',
            component: () => import('@/views/forms/Layout.vue'),
          },
          {
            path: '/forms/validation',
            name: 'Validation',
            component: () => import('@/views/forms/Validation.vue'),
          },
        ],
      },
      {
        path: '/charts',
        name: 'Charts',
        component: () => import('@/views/charts/Charts.vue'),
      },
      {
        path: '/icons',
        name: 'Icons',
        component: {
          render() {
            return h(resolveComponent('router-view'))
          },
        },
        redirect: '/icons/coreui-icons',
        children: [
          {
            path: '/icons/coreui-icons',
            name: 'CoreUI Icons',
            component: () => import('@/views/icons/CoreUIIcons.vue'),
          },
          {
            path: '/icons/brands',
            name: 'Brands',
            component: () => import('@/views/icons/Brands.vue'),
          },
          {
            path: '/icons/flags',
            name: 'Flags',
            component: () => import('@/views/icons/Flags.vue'),
          },
        ],
      },
      {
        path: '/notifications',
        name: 'Notifications',
        component: {
          render() {
            return h(resolveComponent('router-view'))
          },
        },
        redirect: '/notifications/alerts',
        children: [
          {
            path: '/notifications/alerts',
            name: 'Alerts',
            component: () => import('@/views/notifications/Alerts.vue'),
          },
          {
            path: '/notifications/badges',
            name: 'Badges',
            component: () => import('@/views/notifications/Badges.vue'),
          },
          {
            path: '/notifications/modals',
            name: 'Modals',
            component: () => import('@/views/notifications/Modals.vue'),
          },
          {
            path: '/notifications/toasts',
            name: 'Toasts',
            component: () => import('@/views/notifications/Toasts.vue'),
          },
        ],
      },
      {
        path: '/widgets',
        name: 'Widgets',
        component: () => import('@/views/widgets/Widgets.vue'),
      },
    ],
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/pages',
    redirect: '/pages/404',
    name: 'Pages',
    component: {
      render() {
        return h(resolveComponent('router-view'))
      },
    },
    children: [
      {
        path: '404',
        name: 'Page404',
        component: () => import('@/views/pages/Page404'),
      },
      {
        path: '500',
        name: 'Page500',
        component: () => import('@/views/pages/Page500'),
      },
      // {
      //   path: 'login',
      //   name: 'Login',
      //   component: () => import('@/views/pages/Login'),
      // },
      {
        path: 'register',
        name: 'Register',
        component: () => import('@/views/pages/Register'),
      },
    ],
  },
]

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes,
  scrollBehavior() {
    // always scroll to top
    return { top: 0 }
  },
})

// Navigation Guard: Bảo vệ các route cần đăng nhập
// router.beforeEach((to, from, next) => {
//   const isAuthenticated = localStorage.getItem('token')  // Kiểm tra token đã lưu
//
//   if (to.matched.some(record => record.name !== 'Login')) {  // Kiểm tra các trang khác ngoài Login
//     if (!isAuthenticated) {
//       next({ name: 'Login' })  // Chuyển hướng đến trang Login nếu chưa đăng nhập
//     } else {
//       next()  // Tiếp tục đến trang yêu cầu nếu đã đăng nhập
//     }
//   } else {
//     next()  // Không yêu cầu đăng nhập, tiếp tục
//   }
// })


export default router
