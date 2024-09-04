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
              <CTableHeaderCell scope="col">Ảnh</CTableHeaderCell>
              <CTableHeaderCell scope="col">Ngày cập nhật</CTableHeaderCell>
              <CTableHeaderCell scope="col"></CTableHeaderCell>
            </CTableRow>
          </CTableHead>
          <CTableBody>
            <CTableRow>
              <CTableHeaderCell scope="row">1</CTableHeaderCell>
              <CTableDataCell>
                <img style="height: 40px;width:60px;" src="http://ddshnamgiangmuongte.vnuforest.com/banner/917cc4ed-6695-403d-bbb4-11be1b32b7e9.JPG" alt="..." class="img-thumbnail">
              </CTableDataCell>
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
              <CTableDataCell>
                <img style="height: 40px;width:60px;" src="" alt="..." class="img-thumbnail">
              </CTableDataCell>
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
              <CTableDataCell>
                <img style="height: 40px;width:60px;" src="" alt="..." class="img-thumbnail">
              </CTableDataCell>
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
      banner: [] // Dữ liệu banner sẽ được tải từ API
    };
  },
  mounted() {
    // Gọi API để lấy danh sách banner và gán vào biến banner
    this.fetchBanner();
  },
  methods: {
    async fetchBanner() {
      try {
        const response = await fetch('/api/banner'); // Thay '/api/banner' bằng endpoint API thực tế của bạn
        const data = await response.json();
        this.banner = data;
      } catch (error) {
        console.error('Lỗi khi tải danh sách banner:', error);
        // Xử lý lỗi (ví dụ: hiển thị thông báo lỗi)
      }
    }
  }
};
</script>
