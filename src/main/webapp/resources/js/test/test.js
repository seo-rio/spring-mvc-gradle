import { result } from './module/test/axiosTest';

let axiosTest = new result();

document.addEventListener('DOMContentLoaded', async () => {
    const show = await axiosTest.render();
    console.log('SHOW@!!', show);
});
