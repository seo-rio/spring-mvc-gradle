import axios from "axios";

export class result {
    async render() {
        let res;
        res = await axios.get('/api/test2');
        console.log('Response test2 !! =>', res.data);
        return res.data;
    }
}