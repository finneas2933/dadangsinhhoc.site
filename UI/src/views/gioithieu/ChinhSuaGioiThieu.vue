<script setup>
</script>

<template>
  <CCol :xs="12">
    <CCard class="mb-4">
      <CCardBody>
        <div class="mb-3 editor-container">
          <ckeditor
            :editor="editor"
            v-model="banner.text"
            :config="editorConfig"
          />
        </div>
      </CCardBody>
    </CCard>
  </CCol>
</template>

<script>
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

export default {
  data() {
    return {
      editor: ClassicEditor,
      editorConfig: {
        // Thêm cấu hình nếu cần thiết
        height:500,
      },
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

