<script setup>
import { ref } from 'vue'
import { CIcon } from '@coreui/icons-vue'
import { cilSearch, cilPlus, cilTrash, cilPencil } from '@coreui/icons'
import { CModal, CModalHeader, CModalTitle, CModalBody, CModalFooter, CButton } from '@coreui/vue'

// Các trạng thái để điều khiển modal
const isModalVisible = ref(false);
const selectedUserId = ref(null); // Lưu trữ ID của người dùng được chọn để xóa

const openDeleteModal = (userId) => {
  selectedUserId.value = userId;
  isModalVisible.value = true;
}

const closeModal = () => {
  isModalVisible.value = false;
  selectedUserId.value = null;
}

// Dữ liệu người dùng giả lập
const users = ref([
  { id: 2, name: 'Trần Thị B', email: 'thib@gmail.com', phone: '0987654321', createdAt: '2024-02-01', lastSigninedTime: '2024-08-02' },
  { id: 1, name: 'Nguyễn Văn A', email: 'vana@gmail.com', phone: '0123456789', createdAt: '2024-01-01', lastSigninedTime: '2024-08-01' },
]);

const deleteUser = () => {
  // Gọi API để xóa người dùng với selectedUserId.value
  users.value = users.value.filter(user => user.id !== selectedUserId.value);
  console.log("Xóa người dùng với ID:", selectedUserId.value);
  // Sau khi xóa thành công, ẩn modal
  closeModal();
}

const goToDetails = (userId) => {
  // Điều hướng đến trang chi tiết của người dùng
  this.$router.push({ name: 'Chi tiết người dùng', params: { id: userId } });
}

// Fetch danh sách người dùng khi component được mount
//const users = ref([]);

const fetchUsers = async () => {
  try {
    const response = await fetch('/api/users'); // Thay '/api/users' bằng endpoint API thực tế của bạn
    const data = await response.json();
    users.value = data;
  } catch (error) {
    console.error('Lỗi khi tải danh sách người dùng:', error);
  }
}

fetchUsers();
</script>

<template>
  <CCol :xs="12">
    <CCard class="mb-4">
      <CCardHeader class="d-flex justify-content-between align-items-center">
        <CButton variant="outline" shape="rounded-0" color="success">
          <CIcon class="me-2" :icon="cilPlus" />Thêm mới
        </CButton> 
        <CInputGroup class="w-50">
          <CFormInput aria-label="Tìm kiếm" aria-describedby="btnGroupAddon" />
          <CInputGroupText id="basic-addon2">
            <CIcon :icon="cilSearch" />
          </CInputGroupText>
          <CButton type="button" color="secondary" variant="outline" id="button-addon2" aria-describedby="btnGroupAddon">Tìm kiếm</CButton>
        </CInputGroup>
      </CCardHeader>
      <CCardBody>
        <CTable hover bordered striped>
          <CTableHead>
            <CTableRow>
              <CTableHeaderCell scope="col">#</CTableHeaderCell>
              <CTableHeaderCell scope="col">Họ tên</CTableHeaderCell>
              <CTableHeaderCell scope="col">Email</CTableHeaderCell>
              <CTableHeaderCell scope="col">SĐT</CTableHeaderCell>
              <CTableHeaderCell scope="col">Ngày tạo</CTableHeaderCell>
              <CTableHeaderCell scope="col">Đăng nhập cuối</CTableHeaderCell>
              <CTableHeaderCell scope="col"></CTableHeaderCell>
            </CTableRow>
          </CTableHead>
          <CTableBody>
            <CTableRow v-for="user in users" :key="user.id" @click="goToDetails(user.id)" style="cursor: pointer;">
              <CTableHeaderCell scope="row">{{ user.id }}</CTableHeaderCell>
              <CTableDataCell>{{ user.name }}</CTableDataCell>
              <CTableDataCell>{{ user.email }}</CTableDataCell>
              <CTableDataCell>{{ user.phone }}</CTableDataCell>
              <CTableDataCell>{{ user.createdAt }}</CTableDataCell>
              <CTableDataCell>{{ user.lastSigninedTime }}</CTableDataCell>
              <CTableDataCell @click.stop>
                <!-- <router-link :to="{ name: 'Chỉnh sửa người dùng', params: { id: user.id } }" class="me-2"> -->
                <router-link :to="{ name: 'Chỉnh sửa người dùng' }" class="me-2">
                  <CIcon class="text-info" :icon="cilPencil" />
                </router-link>
                <a href="javascript:void(0)" @click="openDeleteModal(user.id)">
                  <CIcon class="text-danger" :icon="cilTrash" />
                </a>
              </CTableDataCell>
            </CTableRow>
          </CTableBody>
        </CTable>
      </CCardBody>
    </CCard>
  </CCol>

  <!-- Modal xác nhận xóa -->
  <CModal :visible="isModalVisible" @close="closeModal">
    <CModalHeader>
      <CModalTitle>Xác nhận xóa</CModalTitle>
    </CModalHeader>
    <CModalBody>Bạn có chắc chắn muốn xóa người dùng này không?</CModalBody>
    <CModalFooter>
      <CButton color="secondary" @click="closeModal">Hủy</CButton>
      <CButton color="danger" @click="deleteUser">Xóa</CButton>
    </CModalFooter>
  </CModal>
</template>

<script>
export default {
  data() {
    return {
      users: [] // Dữ liệu người dùng sẽ được tải từ API
    };
  },
  mounted() {
    // Gọi API để lấy danh sách người dùng và gán vào biến users
    this.fetchUsers();
  },
  methods: {
    async fetchUsers() {
      try {
        const response = await fetch('/api/users'); // Thay '/api/users' bằng endpoint API thực tế của bạn
        const data = await response.json();
        this.users = data;
      } catch (error) {
        console.error('Lỗi khi tải danh sách người dùng:', error);
        // Xử lý lỗi (ví dụ: hiển thị thông báo lỗi)
      }
    }
  }
};
</script>
