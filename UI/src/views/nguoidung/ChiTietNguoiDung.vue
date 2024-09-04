<script setup>
import { CIcon } from '@coreui/icons-vue'
import { cilTrash, cilPencil } from '@coreui/icons'

</script>

<template>
    <CCol :xs="12">
        <CCard class="mb-4">
            <CCardBody>
                <CTable striped>
                    <CTableBody>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Họ tên:</CTableHeaderCell>
                            <CTableDataCell>Mark</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Email:</CTableHeaderCell>
                            <CTableDataCell>Jacob@gmail.com</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Số điện thoại:</CTableHeaderCell>
                            <CTableDataCell>099.999.9999</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Giới tính:</CTableHeaderCell>
                            <CTableDataCell>Nam</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Ngày sinh:</CTableHeaderCell>
                            <CTableDataCell>2002/03/20</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Địa chỉ:</CTableHeaderCell>
                            <CTableDataCell>Hà Nội</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Ngày đăng ký:</CTableHeaderCell>
                            <CTableDataCell>2024/08/13</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Ngày cập nhật:</CTableHeaderCell>
                            <CTableDataCell>2024/09/02</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Lần cuối đăng nhập:</CTableHeaderCell>
                            <CTableDataCell>2024/09/03</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Trạng thái:</CTableHeaderCell>
                            <CTableDataCell>Bật</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Nhóm người dùng:</CTableHeaderCell>
                            <CTableDataCell>Admin</CTableDataCell>
                        </CTableRow>
                    </CTableBody>
                </CTable>
                <div class="d-flex justify-content-left mb-3">
                    <CButton variant="outline" shape="rounded-0" color="success" class="me-2"
                        @click="chuyenDenTrangChinhSua">
                        <CIcon class="me-2" :icon="cilPencil" />Chỉnh sửa
                    </CButton>
                    <CButton variant="outline" shape="rounded-0" color="info" @click="chuyenDenTrangDoiMatKhau">
                        <CIcon class="me-2" :icon="cilTrash" />Xóa
                    </CButton>
                </div>
            </CCardBody>
        </CCard>
    </CCol>

    <CModal :visible="showModal" @close="closeModal">
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
    methods: {
        chuyenDenTrangChinhSua() {
            this.$router.push({ name: 'Chỉnh sửa người dùng' });
        }
    }
};
</script>
