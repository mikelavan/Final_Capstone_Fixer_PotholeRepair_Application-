import axios from 'axios';

export default{
    
    list(){
        return axios.get('/potholes');
    },

    deletePothole(id) {
        return axios.delete(`/potholes/${id}`)
    },

    createReport(data) {
        return axios.post('/potholes', data)
    }
}
