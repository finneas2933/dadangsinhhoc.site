<script setup>
import { ref, computed } from 'vue'
import { useRoute } from 'vue-router'
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
    idBo: '',
    status: true
});
const newHo = ref({
    name: '',
    nameLatinh: '',
    idBo: '',
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

const deleteHo = async () => {
    try {
        // Gửi yêu cầu DELETE đến backend để xóa họ
        const response = await fetch(`http://localhost:8080/api/Ho/deleteHo/{id}${selectedHoId.value}`, {
            method: 'DELETE',
        });

        if (!response.ok) throw new Error('Có lỗi xảy ra khi xóa họ');

        // Cập nhật lại danh sách trên frontend
        hos.value = hos.value.filter(ho => ho.id !== selectedHoId.value);
        closeDeleteModal(); // Đóng modal sau khi xóa thành công

        console.log('Xóa với ID: ', selectedHoId.value);
    } catch (error) {
        console.error('Lỗi khi xóa:', error);
        // Bạn có thể thêm thông báo lỗi cho người dùng
    }
}

const danhSachBo = computed(() => {
    return bos.value.map(bo => ({
        label: bo.name,
        value: bo.id
    }))
})

const saveEditedHo = async () => {
    try {
        const currentDate = new Date().toISOString().split('T')[0];
        const editedHoWithMetadata = {
            ...editingHo.value,
            loai: isDongVat.value,
            createdAt: '',
            createdBy: '',
            updatedAt: currentDate,
            updatedBy: 'Current User' // Thay thế bằng logic lấy thông tin người dùng hiện tại
        };

        // Gửi yêu cầu PUT đến backend để chỉnh sửa
        const response = await fetch(`http://localhost:8080/api/Ho/updateHo/${editedHoWithMetadata.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(editedHoWithMetadata),
        });

        console.log(editedHoWithMetadata);

        if (!response.ok) throw new Error('Có lỗi xảy ra khi chỉnh sửa họ');

        // Cập nhật lại danh sách họ trên frontend
        const updatedHo = await response.json();
        const index = hos.value.findIndex(ho => ho.id === updatedHo.id);
        if (index !== -1) {
            hos.value[index] = { ...updatedHo };  // Cập nhật dữ liệu của họ vừa chỉnh sửa
        }
        closeEditModal();  // Đóng modal sau khi chỉnh sửa thành công

        console.log('Chỉnh sửa thành công:', updatedHo);
    } catch (error) {
        console.error('Lỗi khi chỉnh sửa:', error);
        // Bạn có thể thêm thông báo lỗi cho người dùng
    }
}

const addNewHo = async () => {
    try {
        const currentDate = new Date().toISOString().split('T')[0];
        const newHoWithMetadata = {
            ...newHo.value,
            id: 1000,
            loai: isDongVat.value,
            createdAt: currentDate,
            createdBy: 'Current User', // Thay thế bằng logic lấy thông tin người dùng hiện tại
            updatedAt: '',
            updatedBy: ''
        };

        console.log("Dữ liệu thêm mới: ", newHoWithMetadata);

        // Lấy token từ localStorage hoặc từ store của bạn
        const token = localStorage.getItem('token');
        console.log("token:", token);

        // Gửi yêu cầu POST đến backend để thêm mới
        const response = await fetch('http://localhost:8080/api/Ho/addNewHo', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}` // Thêm token vào header
            },
            body: JSON.stringify(newHoWithMetadata),
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Có lỗi xảy ra khi thêm mới họ');
        }

        // Thêm mới vào danh sách nếu thành công
        const addedHo = await response.json();
        hos.value.push(addedHo);  // Cập nhật danh sách trên frontend
        closeAddModal();  // Đóng modal sau khi thêm thành công

        console.log('Thêm mới thành công:', addedHo);
    } catch (error) {
        console.error('Lỗi khi thêm mới:', error);
        // Bạn có thể thêm thông báo lỗi cho người dùng
    }
}

//Lấy route hiện tại để xác định 'dong-vat' hoặc 'thuc-vat'
const route = useRoute();
const isDongVat = ref(route.path.includes('/dong-vat')); // Kiểm tra route có chứa '/dong-vat' không
console.log('Is Động Vật:', isDongVat.value);

const hos = ref([]);

// Fetch danh sách khi component được mount
const fetchHos = async () => {
    const data = ref([]);
    const error = ref(null);
    try {
        const response = await fetch(`http://localhost:8080/api/Ho/getAllHoByLoai/${isDongVat.value}`);
        if (!response.ok) throw new Error("Some thing went wrong...");
        data.value = await response.json();
        hos.value = data.value.data;
        console.log("Danh sách: ", hos.value);
    } catch (err) {
        error.value = err;
        console.log('Lỗi khi tải danh sách:', error.value);
    }
}
fetchHos();

const bos = ref([]);
// Bảng khóa ngoại
const fetchBos = async () => {
    const data = ref([]);
    const error = ref(null);
    try {
        const response = await fetch(`http://localhost:8080/api/bo/getAllBoByLoai/${isDongVat.value}`);
        if (!response.ok) throw new Error("Some thing went wrong...");
        data.value = await response.json();
        bos.value = data.value.data;
        console.log("Danh sách bảng khóa ngoại: ", bos.value);
    } catch (err) {
        error.value = err;
        console.log('Lỗi khi tải danh sách:', error.value);
    }
}
fetchBos();

const getBoName = (idBo) => {
    const bo = bos.value.find(b => b.id === idBo);
    return bo ? bo.name : '';
};
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
                            <CTableDataCell>{{ getBoName(ho.idBo) }}</CTableDataCell>
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
                    <CFormInput id="editNameLatinh" v-model="editingHo.nameLatinh" required />
                </div>
                <div class="mb-3">
                    <CFormLabel for="editBo">Bộ:</CFormLabel>
                    <CFormSelect id="editBo" v-model="editingHo.idBo" :options="danhSachBo" required />
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
                    <CFormSelect id="addBo" v-model="newHo.idBo" :options="danhSachBo" required />
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