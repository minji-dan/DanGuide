//let map = require('./indoor/map/TestMap.json');

const express = require('express')
const app = express();
const port = process.env.PORT || 3000;

app.get('/indoor', (req, res) => {
    res.sendFile(__dirname + '/indoor/indoor.html');
});

app.get('/indoor/script', (req, res) => {
    res.sendFile(__dirname + '/indoor/dist/indoor_pack.js');
});

app.get('/API/indoor/map', (req, res) => {
    res.sendFile(__dirname + '/indoor/maps/TestMap/Test_floor_1.json');
});

app.get('/API/indoor/map/image', (req, res) => {
    res.sendFile(__dirname + '/indoor/maps/TestMap/Test_floor_1.gif');
});

app.listen(port, () => {
    console.log(`server is listening at 10.0.2.2:${port}`);
});