<script setup>
import { CIcon } from '@coreui/icons-vue'
import { cilSearch } from '@coreui/icons'  // Import icon cil-search
import { cilPlus } from '@coreui/icons'  // Import icon cil-Plus
import { cilTrash } from '@coreui/icons'
import { cilPencil } from '@coreui/icons'

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
            <CTableRow>
              <CTableHeaderCell scope="row">1</CTableHeaderCell>
              <CTableDataCell>Mark</CTableDataCell>
              <CTableDataCell>Otto</CTableDataCell>
              <CTableDataCell>@mdo</CTableDataCell>
              <CTableDataCell>Mark</CTableDataCell>
              <CTableDataCell>Otto</CTableDataCell>
              <CTableDataCell>
                <a class="me-2" href="">
                  <CIcon class="text-info" :icon="cilPencil" />
                </a>
                <a href="">
                  <CIcon class="text-danger" :icon="cilTrash" />
                </a>
              </CTableDataCell>
            </CTableRow>
            <CTableRow>
              <CTableHeaderCell scope="row">2</CTableHeaderCell>
              <CTableDataCell>Jacob</CTableDataCell>
              <CTableDataCell>Thornton</CTableDataCell>
              <CTableDataCell>@fat</CTableDataCell>
              <CTableDataCell>Jacob</CTableDataCell>
              <CTableDataCell>Thornton</CTableDataCell>
              <CTableDataCell>
                <a class="me-2" href="">
                  <CIcon class="text-info" :icon="cilPencil" />
                </a>
                <a href="">
                  <CIcon class="text-danger" :icon="cilTrash" />
                </a>
              </CTableDataCell>
            </CTableRow>
            <CTableRow>
              <CTableHeaderCell scope="row">3</CTableHeaderCell>
              <CTableDataCell>Larry the Bird</CTableDataCell>
              <CTableDataCell></CTableDataCell>
              <CTableDataCell>@twitter</CTableDataCell>
              <CTableDataCell>Larry the Bird</CTableDataCell>
              <CTableDataCell></CTableDataCell>
              <CTableDataCell>
                <a class="me-2" href="">
                  <CIcon class="text-info" :icon="cilPencil" />
                </a>
                <a href="">
                  <CIcon class="text-danger" :icon="cilTrash" />
                </a>
              </CTableDataCell>
            </CTableRow>
          </CTableBody>
        </CTable>
      </CCardBody>
    </CCard>
  </CCol>
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
