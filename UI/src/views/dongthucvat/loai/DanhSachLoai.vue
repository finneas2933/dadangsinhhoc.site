<script setup>
import { ref } from 'vue'
import { CIcon } from '@coreui/icons-vue'
import { cilSearch, cilPlus, cilTrash, cilPencil } from '@coreui/icons'
import {
    CCol,
    CCard,
    CCardHeader,
    CButton,
    CInputGroup,
    CFormInput,
    CInputGroupText,
    CCardBody,
    CTable,
    CTableHead,
    CTableRow,
    CTableHeaderCell,
    CTableBody,
    CTableDataCell,
    CModal,
    CModalHeader,
    CModalTitle,
    CModalBody,
    CModalFooter
} from '@coreui/vue';

// Các trạng thái để điều khiển modal
const isModalVisible = ref(false);
const selectedLoaiId = ref(null); // Lưu trữ ID được chọn để xóa

const openDeleteModal = (loaiId) => {
    selectedLoaiId.value = loaiId;
    isModalVisible.value = true;
}

const closeModal = () => {
    isModalVisible.value = false;
    selectedLoaiId.value = null;
}

// Dữ liệu giả lập
const loais = ref([
    {
        id: 1,
        name: 'Hổ Đông Dương',
        nameLatinh: 'Panthera tigris tigris',
        ho: 'Họ Mèo (Felidae)',
        updatedAt: '2023-05-15',
        rPH: 'RPH Nam Giang'
    },
    {
        id: 2,
        name: 'Voọc mũi hếch',
        nameLatinh: 'Rhinopithecus avunculus',
        ho: 'Họ Khỉ Cựu Thế giới (Cercopithecidae)',
        updatedAt: '2023-08-20',
        rPH: 'RPH Mường Tè'
    },
    {
        id: 3,
        name: 'Pơ mu',
        nameLatinh: 'Fokienia hodginsii',
        ho: 'Họ Hoàng đàn (Cupressaceae)',
        updatedAt: '2023-11-10',
        rPH: 'RPH Nam Giang'
    },
    {
        id: 4,
        name: 'Đỗ quyên',
        nameLatinh: 'Rhododendron spp.',
        ho: 'Họ Thạch nam (Ericaceae)',
        updatedAt: '2023-06-03',
        rPH: 'RPH Mường Tè'
    }
]);

const deleteLoai = () => {
    // Gọi API để xóa với selectedLoaiId.value
    loais.value = loais.value.filter(loai => loai.id !== selectedLoaiId.value);
    console.log("Xóa với ID:", selectedLoaiId.value);
    // Sau khi xóa thành công, ẩn modal
    closeModal();
}

// Fetch danh sách khi component được mount
const fetchloais = async () => {
    try {
        const response = await fetch('/api/loais');
        const data = await response.json();
        loais.value = data;
    } catch (error) {
        console.error('Lỗi khi tải danh sách:', error);
    }
}

fetchloais();
</script>

<template>
    <CCol :xs="12">
        <CCard class="mb-4">
            <CCardHeader class="d-flex justify-content-between align-items-center">
                <router-link :to="{ name: 'Thêm mới loài động vật' }">
                    <CButton variant="outline" shape="rounded-0" color="success">
                        <CIcon class="me-2" :icon="cilPlus" />Thêm mới
                    </CButton>
                </router-link>
                <CInputGroup class="w-50">
                    <CFormInput aria-label="Tìm kiếm" aria-describedby="btnGroupAddon" />
                    <CInputGroupText id="basic-addon2">
                        <CIcon :icon="cilSearch" />
                    </CInputGroupText>
                    <CButton type="button" color="secondary" variant="outline" id="button-addon2"
                        aria-describedby="btnGroupAddon">Tìm kiếm</CButton>
                </CInputGroup>
            </CCardHeader>
            <CCardBody>
                <CTable hover bordered striped>
                    <CTableHead>
                        <CTableRow>
                            <CTableHeaderCell scope="col">STT</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Tên tiếng Việt</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Tên Latinh</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Họ</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Ngày cập nhật</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Rừng phòng hộ</CTableHeaderCell>
                            <CTableHeaderCell scope="col"></CTableHeaderCell>
                        </CTableRow>
                    </CTableHead>
                    <CTableBody>
                        <CTableRow v-for="(loai, index) in loais" :key="loai.id">
                            <CTableHeaderCell scope="row">{{ index + 1 }}</CTableHeaderCell>
                            <CTableDataCell>{{ loai.name }}</CTableDataCell>
                            <CTableDataCell>{{ loai.nameLatinh }}</CTableDataCell>
                            <CTableDataCell>{{ loai.ho }}</CTableDataCell>
                            <CTableDataCell>{{ loai.updatedAt }}</CTableDataCell>
                            <CTableDataCell>{{ loai.rPH }}</CTableDataCell>
                            <CTableDataCell @click.stop>
                                <!-- <router-link :to="{ name: 'Chỉnh sửa loài', params: { id: loai.id } }" class="me-2"> -->
                                <router-link :to="{ name: 'Chỉnh sửa loài động vật' }" class="me-2">
                                    <CIcon class="text-info" :icon="cilPencil" />
                                </router-link>
                                <a href="javascript:void(0)" @click="openDeleteModal(loai.id)">
                                    <CIcon class="text-danger" :icon="cilTrash" />
                                </a>
                            </CTableDataCell>
                        </CTableRow>
                    </CTableBody>
                </CTable>
            </CCardBody>
        </CCard>
    </CCol>

    <CModal :visible="isModalVisible" @close="closeModal">
        <CModalHeader>
            <CModalTitle>Xác nhận xóa</CModalTitle>
        </CModalHeader>
        <CModalBody>Bạn có chắc chắn muốn xóa không?</CModalBody>
        <CModalFooter>
            <CButton color="secondary" @click="closeModal">Hủy</CButton>
            <CButton color="danger" @click="deleteLoai">Xóa</CButton>
        </CModalFooter>
    </CModal>
</template>