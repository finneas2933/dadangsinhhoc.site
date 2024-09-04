<template>
    <div>
      <h1>Danh sách người dùng</h1>
  
      <CTable>
        <CTableHead>
          <CTableRow>
            <CTableHeaderCell scope="col">#</CTableHeaderCell>
            <CTableHeaderCell scope="col">Tên</CTableHeaderCell>
            <CTableHeaderCell scope="col">Email</CTableHeaderCell>
            <CTableHeaderCell scope="col">Vai trò</CTableHeaderCell>
            {/* ... các cột khác nếu cần */}
          </CTableRow>
        </CTableHead>
        <CTableBody>
          <CTableRow v-for="(user, index) in users" :key="user.id">
            <CTableDataCell>{{ index + 1 }}</CTableDataCell>
            <CTableDataCell>{{ user.name }}</CTableDataCell>
            <CTableDataCell>{{ user.email }}</CTableDataCell>
            <CTableDataCell>{{ user.role }}</CTableDataCell>
            {/* ... các cột khác nếu cần */}
          </CTableRow>
        </CTableBody>
      </CTable>
    </div>
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