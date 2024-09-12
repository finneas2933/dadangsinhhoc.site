<script setup>
import AppFooter from '@/components/AppFooterGuest.vue'
import AppHeader from '@/components/AppHeaderGuest.vue'
</script>

<template>
    <AppHeader />
    <div class="content">
        <div class="container">
            <marquee direction="left" behavior="scroll" scrollamount="3" class="mt-2">
                <h2 style="font-size:2rem;">
                    Cơ sở dữ liệu đa dạng sinh học
                </h2>
            </marquee>
            <div class="filter mx-5">
                <div class="row">
                    <div class="col-3 mb-2">
                        <label for="">Ngành:</label>
                        <select name="" id="" class="form-control">
                            <option value="">Ngành</option>
                            <option value="">a</option>
                        </select>
                    </div>
                    <div class="col-3 mb-2">
                        <label for="">Lớp:</label>
                        <select name="" id="" class="form-control">
                            <option value="">Lớp</option>
                            <option value="">a</option>
                        </select>
                    </div>
                    <div class="col-3 mb-2">
                        <label for="">Bộ:</label>
                        <select name="" id="" class="form-control">
                            <option value="">Bộ</option>
                            <option value="">a</option>
                        </select>
                    </div>
                    <div class="col-3 mb-2">
                        <label for="">Họ:</label>
                        <select name="" id="" class="form-control">
                            <option value="">Họ</option>
                            <option value="">a</option>
                        </select>
                    </div>
                    <div class="col-12">
                        <div class="row">
                            <div class="col-3 mb-2">
                                <label for="">Mức độ bảo tồn:</label>
                                <select name="" id="" class="form-control">
                                    <option value="">ICUN</option>
                                    <option value="">a</option>
                                </select>
                            </div>
                            <div class="col-3 mb-2">
                                <label for=""></label>
                                <select name="" id="" class="form-control">
                                    <option value="">Sách đỏ việt nam</option>
                                    <option value="">a</option>
                                </select>
                            </div>
                            <div class="col-3 mb-2">
                                <label for=""></label>
                                <select name="" id="" class="form-control">
                                    <option value="">Nghị định 84/ NĐ-CP</option>
                                    <option value="">a</option>
                                </select>
                            </div>
                            <div class="col-3 mb-2">
                                <label for=""></label>
                                <select name="" id="" class="form-control">
                                    <option value="">Nghị định 64/ NĐ-CP</option>
                                    <option value="">a</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="row">
                            <div class="col-9 mb-2">
                                <label for="">Chọn rừng phòng hộ:</label>
                                <select name="" id="" class="form-control">
                                    <option value="">Tất cả</option>
                                    <option value="">Nam Giang</option>
                                    <option value="">Mường Tè</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="row">
                            <div class="col-9">
                                <div class="row">
                                    <div class="col-6">
                                        <label for="">Tên tiếng Việt:</label>
                                        <input type="text" name="" id="" class="form-control">
                                    </div>
                                    <div class="col-6">
                                        <label for="">Tên khoa học:</label>
                                        <input type="text" name="" id="" class="form-control">
                                    </div>
                                </div>
                            </div>
                            <div class="col-3 d-flex align-items-end">
                                <button class="btn btn-primary"><i class="fa-solid fa-magnifying-glass me-1"></i>Tìm
                                    kiếm</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="list-animal mx-5">
                <h3>Danh sách động vật- Số lượng: 489</h3>
                <div class="row">
                    <div class="col-md-6 col-xs-12 animal-item">
                        <div class="row">
                            <div class="col-2">
                                <div class="stt">01.</div>
                            </div>
                            <div class="col-4">
                                <img src="http://ddshnamgiangmuongte.vnuforest.com/user-content/1842-1.jpg" alt=""
                                    class="h-100 w-100">
                            </div>
                            <div class="col-6">
                                <div class="noidung">
                                    <h5>Loài: Ếch cây trung bộ</h5>
                                    <p>Họ: Ếch cây</p>
                                    <p>BỘ KHÔNG ĐUÔI</p>
                                    <p>LỚP LƯỠNG CƯ</p>
                                    <p>Ngành có dây sống</p>
                                    <router-link to="/" style="color: green; font-weight: bold;">CHI TIẾT</router-link>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-xs-12 animal-item">
                        <div class="row">
                            <div class="col-2">
                                <div class="stt">01.</div>
                            </div>
                            <div class="col-4">
                                <img src="http://ddshnamgiangmuongte.vnuforest.com/user-content/1842-1.jpg" alt=""
                                    class="h-100 w-100">
                            </div>
                            <div class="col-6">
                                <div class="noidung">
                                    <h5>Loài: Ếch cây trung bộ</h5>
                                    <p>Họ: Ếch cây</p>
                                    <p>BỘ KHÔNG ĐUÔI</p>
                                    <p>LỚP LƯỠNG CƯ</p>
                                    <p>Ngành có dây sống</p>
                                    <router-link to="/" style="color: green; font-weight: bold;">CHI TIẾT</router-link>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-xs-12 animal-item">
                        <div class="row">
                            <div class="col-2">
                                <div class="stt">01.</div>
                            </div>
                            <div class="col-4">
                                <img src="http://ddshnamgiangmuongte.vnuforest.com/user-content/1842-1.jpg" alt=""
                                    class="h-100 w-100">
                            </div>
                            <div class="col-6">
                                <div class="noidung">
                                    <h5>Loài: Ếch cây trung bộ</h5>
                                    <p>Họ: Ếch cây</p>
                                    <p>BỘ KHÔNG ĐUÔI</p>
                                    <p>LỚP LƯỠNG CƯ</p>
                                    <p>Ngành có dây sống</p>
                                    <router-link to="/" style="color: green; font-weight: bold;">CHI TIẾT</router-link>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-xs-12 animal-item">
                        <div class="row">
                            <div class="col-2">
                                <div class="stt">01.</div>
                            </div>
                            <div class="col-4">
                                <img src="http://ddshnamgiangmuongte.vnuforest.com/user-content/1842-1.jpg" alt=""
                                    class="h-100 w-100">
                            </div>
                            <div class="col-6">
                                <div class="noidung">
                                    <h5>Loài: Ếch cây trung bộ</h5>
                                    <p>Họ: Ếch cây</p>
                                    <p>BỘ KHÔNG ĐUÔI</p>
                                    <p>LỚP LƯỠNG CƯ</p>
                                    <p>Ngành có dây sống</p>
                                    <router-link to="/" style="color: green; font-weight: bold;">CHI TIẾT</router-link>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="mt-4">
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                        <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                        <li class="page-item active"><a class="page-link " href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">Next</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
    <AppFooter />
</template>

<script>
import 'https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js';
export default {

};
</script>

<style scoped>
@import 'https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css';

a {
    text-decoration: none;
}

ul {
    list-style-type: none;
}

.nav-justified .nav-item .nav-link {
    width: 80%;
}

.nav-pills .nav-link {
    border: 1px solid #dee2e6 !important;
    color: black;
    font-size: 15px;
}

/* content */
.content .container {
    max-width: 1100px;
}

.list-animal {
    margin-top: 20px;
}

.list-animal .animal-item {
    margin-top: 20px;
    margin-bottom: 10px;
}

.list-animal .animal-item .stt {
    float: right;
    font-size: 40px;
    color: #CECECE;
}

.list-animal .noidung {
    font-size: 12px;
}

.list-animal .noidung p,
.list-animal .noidung a {
    margin-bottom: 5px;
}
</style>