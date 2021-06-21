import axios from "axios";

export class result {
    async render() {
        let res;
        res = await axios.get('/api/test');
        console.log('Response test1 !! =>', res.data);
        return res.data;
    }
}