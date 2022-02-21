import http from "../http-common"

class DataService {
    getAll() {
        return http.get("/tutorials");
    }

    get(id: number) {
        return http.get(`/tutorials/${id}`);
    }

    create(data: Object) {
        return http.post("/tutorials", data);
    }

    update(id: number, data: Object) {
        return http.put(`/tutorials/${id}`, data);
    }

    delete(id: number) {
        return http.delete(`/tutorials/${id}`);
    }

    deleteAll() {
        return http.delete("/tutorials");
    }

    findByTitle(title: string) {
        return http.get(`/tutorials?title=${title}`);
    }

}

export default new DataService();