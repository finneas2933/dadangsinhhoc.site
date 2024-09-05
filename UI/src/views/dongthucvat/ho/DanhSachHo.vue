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
    CModalFooter,
    CForm,
    CFormLabel,
    CFormCheck
} from '@coreui/vue';

// Các trạng thái để điều khiển modal
const isDeleteModalVisible = ref(false);
const isEditModalVisible = ref(false);
const isAddModalVisible = ref(false);
const selectedHoId = ref(null);
const editingHo = ref({
    name: '',
    nameLatinh: '',
    bo: '',
    status: true
});
const newHo = ref({
    name: '',
    nameLatinh: '',
    bo: '',
    status: true
});

const openDeleteModal = (hoId) => {
    selectedHoId.value = hoId;
    isDeleteModalVisible.value = true;
}

const openEditModal = (ho) => {
    editingHo.value = { ...ho, status: true }; // Assuming status is not in the original data
    isEditModalVisible.value = true;
}

const openAddModal = () => {
    newHo.value = { name: '', nameLatinh: '', status: true };
    isAddModalVisible.value = true;
}

const closeDeleteModal = () => {
    isDeleteModalVisible.value = false;
    selectedHoId.value = null;
}

const closeEditModal = () => {
    isEditModalVisible.value = false;
    editingHo.value = { name: '', nameLatinh: '', status: true };
}

const closeAddModal = () => {
    isAddModalVisible.value = false;
    newHo.value = { name: '', nameLatinh: '', status: true };
}

// Dữ liệu giả lập
const hos = ref([
    {
        id: 1,
        name: 'Hổ Đông Dương',
        nameLatinh: 'Panthera tigris tigris',
        bo: 'Panthera tigris tigris',
        updatedAt: '2023-05-15',
        createdBy: 'createdBy Nam Giang'
    },
    {
        id: 2,
        name: 'Voọc mũi hếch',
        nameLatinh: 'Rhinopithecus avunculus',
        bo: 'Rhinopithecus avunculus',
        updatedAt: '2023-08-20',
        createdBy: 'createdBy Mường Tè'
    },
    {
        id: 3,
        name: 'Pơ mu',
        nameLatinh: 'Fokienia hodginsii',
        updatedAt: '2023-11-10',
        createdBy: 'createdBy Nam Giang'
    },
    {
        id: 4,
        name: 'Đỗ quyên',
        nameLatinh: 'Rhododendron spp.',
        updatedAt: '2023-06-03',
        createdBy: 'createdBy Mường Tè'
    }
]);

const deleteHo = () => {
    hos.value = hos.value.filter(ho => ho.id !== selectedHoId.value);
    console.log("Xóa với ID:", selectedHoId.value);
    closeDeleteModal();
}

const saveEditedHo = () => {
    const index = hos.value.findIndex(ho => ho.id === editingHo.value.id);
    if (index !== -1) {
        hos.value[index] = { ...hos.value[index], ...editingHo.value };
    }
    closeEditModal();
}

const addNewHo = () => {
    const newId = Math.max(...hos.value.map(n => n.id)) + 1;
    const currentDate = new Date().toISOString().split('T')[0];
    const newHoWithMetadata = {
        ...newHo.value,
        id: newId,
        updatedAt: currentDate,
        createdBy: 'Current User' // Replace with actual current user logic
    };
    hos.value.push(newHoWithMetadata);
    closeAddModal();
}

// Fetch danh sách khi component được mount
const fetchhos = async () => {
    try {
        const response = await fetch('/api/ho');
        const data = await response.json();
        hos.value = data;
    } catch (error) {
        console.error('Lỗi khi tải danh sách:', error);
    }
}

fetchhos();
</script>

<template>
    <CCol :xs="12">
        <CCard class="mb-4">
            <CCardHeader class="d-flex justify-content-between align-items-center">
                <CButton variant="outline" shape="rounded-0" color="success" @click="openAddModal">
                    <CIcon class="me-2" :icon="cilPlus" />Thêm mới
                </CButton>
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
                            <CTableHeaderCell scope="col">Bộ</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Ngày cập nhật</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Người tạo</CTableHeaderCell>
                            <CTableHeaderCell scope="col"></CTableHeaderCell>
                        </CTableRow>
                    </CTableHead>
                    <CTableBody>
                        <CTableRow v-for="(ho, index) in hos" :key="ho.id">
                            <CTableHeaderCell scope="row">{{ index + 1 }}</CTableHeaderCell>
                            <CTableDataCell>{{ ho.name }}</CTableDataCell>
                            <CTableDataCell>{{ ho.nameLatinh }}</CTableDataCell>
                            <CTableDataCell>{{ ho.bo }}</CTableDataCell>
                            <CTableDataCell>{{ ho.updatedAt }}</CTableDataCell>
                            <CTableDataCell>{{ ho.createdBy }}</CTableDataCell>
                            <CTableDataCell>
                                <a href="javascript:void(0)" @click="openEditModal(ho)" class="me-2">
                                    <CIcon class="text-info" :icon="cilPencil" />
                                </a>
                                <a href="javascript:void(0)" @click="openDeleteModal(ho.id)">
                                    <CIcon class="text-danger" :icon="cilTrash" />
                                </a>
                            </CTableDataCell>
                        </CTableRow>
                    </CTableBody>
                </CTable>
            </CCardBody>
        </CCard>
    </CCol>

    <!-- Modal Xóa -->
    <CModal :visible="isDeleteModalVisible" @close="closeDeleteModal">
        <CModalHeader>
            <CModalTitle>Xác nhận xóa</CModalTitle>
        </CModalHeader>
        <CModalBody>Bạn có chắc chắn muốn xóa không?</CModalBody>
        <CModalFooter>
            <CButton color="secondary" @click="closeDeleteModal">Hủy</CButton>
            <CButton color="danger" @click="deleteHo">Xóa</CButton>
        </CModalFooter>
    </CModal>

    <!-- Modal Chỉnh sửa -->
    <CModal :visible="isEditModalVisible" @close="closeEditModal">
        <CModalHeader>
            <CModalTitle>Chỉnh sửa họ</CModalTitle>
        </CModalHeader>
        <CModalBody>
            <CForm>
                <div class="mb-3">
                    <CFormLabel for="editNameVi">Tên tiếng Việt:</CFormLabel>
                    <CFormInput id="editNameVi" v-model="editingHo.name" />
                </div>
                <div class="mb-3">
                    <CFormLabel for="editNameLatinh">Tên Latinh:</CFormLabel>
                    <CFormInput id="editNameLatinh" v-model="editingHo.nameLatinh" required/>
                </div>
                <div class="mb-3">
                    <CFormLabel for="editBo">Bộ:</CFormLabel>
                    <CFormSelect id="editBo" v-model="editingHo.bo" :options="danhSachBo" required />
                </div>
                <div class="mb-3">
                    <CFormLabel>Trạng thái:</CFormLabel>
                    <CFormCheck id="editStatusOn" label="Bật" name="editStatus" :checked="editingHo.status"
                        @change="editingHo.status = true" />
                    <CFormCheck id="editStatusOff" label="Tắt" name="editStatus" :checked="!editingHo.status"
                        @change="editingHo.status = false" />
                </div>
            </CForm>
        </CModalBody>
        <CModalFooter>
            <CButton color="secondary" @click="closeEditModal">Hủy</CButton>
            <CButton color="primary" @click="saveEditedHo">Lưu thay đổi</CButton>
        </CModalFooter>
    </CModal>

    <!-- Modal Thêm mới -->
    <CModal :visible="isAddModalVisible" @close="closeAddModal">
        <CModalHeader>
            <CModalTitle>Thêm họ mới</CModalTitle>
        </CModalHeader>
        <CModalBody>
            <CForm>
                <div class="mb-3">
                    <CFormLabel for="addNameVi">Tên tiếng Việt:</CFormLabel>
                    <CFormInput id="addNameVi" v-model="newHo.name" />
                </div>
                <div class="mb-3">
                    <CFormLabel for="addNameLatinh">Tên Latinh:</CFormLabel>
                    <CFormInput id="addNameLatinh" v-model="newHo.nameLatinh" />
                </div>
                <div class="mb-3">
                    <CFormLabel for="addBo">Bộ:</CFormLabel>
                    <CFormSelect id="addBo" v-model="newHo.bo" :options="danhSachBo" required />
                </div>
                <div class="mb-3">
                    <CFormLabel>Trạng thái:</CFormLabel>
                    <CFormCheck id="addStatusOn" label="Bật" name="addStatus" :checked="newHo.status"
                        @change="newHo.status = true" />
                    <CFormCheck id="addStatusOff" label="Tắt" name="addStatus" :checked="!newHo.status"
                        @change="newHo.status = false" />
                </div>
            </CForm>
        </CModalBody>
        <CModalFooter>
            <CButton color="secondary" @click="closeAddModal">Hủy</CButton>
            <CButton color="primary" @click="addNewHo">Thêm mới</CButton>
        </CModalFooter>
    </CModal>
</template>