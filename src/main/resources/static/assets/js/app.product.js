class App {
    static SweetAlert = class {
        static showSuccessAlert(t) {
            Swal.fire({
                icon: 'success',
                title: t,
                position: 'top-end',
                showConfirmButton: false,
                timer: 5000
            })
        }

        static showErrorAlert(t) {
            Swal.fire({
                icon: 'error',
                title: 'Warning',
                position: 'top-end',
                text: t,
                timer: 2000
            })
        }

        /*Hiển thị xóa*/
        static showConfirmDelete() {
            return Swal.fire({
                title: 'Are you sure to deactive the selected customer ?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085D6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, deactive it!',

            });
        }

        /*Hiển thị khôi phục(Restore) nên tắt icon*/
        static showConfirmRestore() {
            return Swal.fire({
                title: 'Are you sure to Restore?',
                icon: 'success',
                showCancelButton: true,
                confirmButtonColor: '#3085D6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, restore it!',
            });
        }
    }

    static IziToast = class  {
        static showErrorAlert(m) {
            iziToast.error({
                title: 'Error',
                position: 'topLeft',
                message: m,
                timer: 2000

            });
        }
        static showSuccessAlert(m) {
            iziToast.success({
                title: 'Success',
                position: 'topLeft',
                message: m,
                timer: 2000
            });
        }
    }

    static formatNumberSpace(x) {
        if (x == null) {
            return x;
        }
        return x.toString().replace(/ /g, "").replace(/\B(?=(\d{3})+(?!\d))/g, " ");
    }
}


class Product{
    constructor(id, title, urlImage, color, price, quantity, category, deleted = 0, createdAt, updatedAt) {
        this.id = id;
        this.title = title;
        this.urlImage = urlImage;
        this.color = color;
        this.price =price;
        this.quantity = quantity;
        this.category = category;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

class Category{
    constructor(id, title) {
        this.id = id;
        this.title = title;
    }
}

class User {
    constructor(id, username, password, phone, fullName, urlImage, createdAt, updatedAt, locationRegion, role, deleted = 0) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.urlImage = urlImage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.locationRegion = locationRegion;
        this.role = role;
        this.deleted = deleted;
    }
}

class Role {
    constructor(id, code) {
        this.id = id;
        this.code = code;
    }
}

class LocationRegion {
    constructor(id, provinceId, provinceName, districtId, districtName, wardId, wardName, address) {
        this.id = id;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.wardId = wardId;
        this.wardName = wardName;
        this.address = address;
    }

}