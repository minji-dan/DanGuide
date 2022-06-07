import * as THREE from './modules/three';
import * as DANG from './modules/danguide';


const mapJson = await fetch('http://10.0.2.2:3000/API/indoor/map') // todo: using header for select map and route
                        .then((response) => response.json());
const mapGraph = new DANG.NavigationGraph(mapJson.data);

const startCoord = [25, 0, 7.5];//[21, 0, 17.6];
const userNode = new DANG.Node("user", "user", startCoord);
const startNode = DANG.findNeighbor(mapGraph, startCoord).name;
const endNode = 'r1-특수촬영실';

const scaleFactor = 20.0;
const ratio = mapJson.map_ratio;

var vec = new THREE.Vector3(0, 0, 0);


const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera( 90, window.innerWidth / window.innerHeight , 0.1, 1000 );

const renderer = new THREE.WebGLRenderer();
renderer.setSize( window.innerWidth, window.innerHeight );
document.body.appendChild( renderer.domElement );

const geometry = new THREE.SphereGeometry();
const wayMesh = new THREE.Mesh( geometry, new THREE.MeshBasicMaterial( { color: 0x909090 } ) );
const roomMesh = new THREE.Mesh( geometry, new THREE.MeshBasicMaterial( { color: 0x900000 } ) );
const stairMesh = new THREE.Mesh( geometry, new THREE.MeshBasicMaterial( { color: 0x909000 } ) );
const elevatorMesh = new THREE.Mesh( geometry, new THREE.MeshBasicMaterial( { color: 0x009000 } ) );
const adjMaterial = new THREE.LineBasicMaterial( {color:0x000000});

mapGraph.getNodes().forEach((node, index, array) => 
{
    var mesh;
    if (node.type == "way") { mesh = wayMesh.clone(); }
    else if (node.type == "room") { mesh = roomMesh.clone(); }
    else if (node.type == "stair") { mesh = stairMesh.clone(); }
    else if (node.type == "elevator") { mesh = elevatorMesh.clone(); }
    scene.add(mesh);
    mesh.scale.set(0.1, 0.05, 0.1);
    const scale = 1;
    mesh.position.set(node.coord[0] * scale, node.coord[1], node.coord[2] * scale);

    mapGraph.adjList[node].forEach((adjEdge, index, array) => {
        const points = [];
        var coord = node.coord;
        points.push(new THREE.Vector3(coord[0], coord[1] + 0.01, coord[2]));
        coord = adjEdge.node.coord;
        points.push(new THREE.Vector3(coord[0], coord[1] + 0.01, coord[2]));
        const lineGeometry = new THREE.BufferGeometry().setFromPoints( points );
        const line = new THREE.Line(lineGeometry, adjMaterial);
        scene.add(line);
    });
});

const map = new THREE.TextureLoader().load(mapJson.image_url);
var planeMaterial = new THREE.MeshBasicMaterial( {map: map, color: 0xffffff});
var planeGeometry = new THREE.PlaneGeometry(scaleFactor * ratio, scaleFactor);
const plane = new THREE.Mesh( planeGeometry, planeMaterial);
plane.rotateX(Math.PI * -0.5);
scene.add(plane);
plane.position.set(scaleFactor * ratio / 2, 0, scaleFactor / 2);

/*

// draw line
const lineMaterial = new THREE.LineBasicMaterial( {color:0xff0000});
for (var i = 1; i < 20; i++)
{
    var points = [];
    points.push(new THREE.Vector3(0, 0.01, scaleFactor * i / 20));
    points.push(new THREE.Vector3(scaleFactor * ratio, 0.01, scaleFactor * i / 20));
    var lineGeometry = new THREE.BufferGeometry().setFromPoints( points );
    var line = new THREE.Line(lineGeometry, lineMaterial);
    scene.add(line);

    points = [];
    points.push(new THREE.Vector3(scaleFactor * ratio * i / 20, 0.01, 0));
    points.push(new THREE.Vector3(scaleFactor * ratio * i / 20, 0.01, scaleFactor));
    lineGeometry = new THREE.BufferGeometry().setFromPoints( points );
    line = new THREE.Line(lineGeometry, lineMaterial);
    scene.add(line);
}
*/

var path = DANG.findRoute(mapGraph, startNode, endNode);  // todo: server request
const pathLine = [path[0].coord[0] - path[1].coord[0], path[0].coord[2] - path[1].coord[2]];
const startLine = [path[0].coord[0] - startCoord[0], path[0].coord[2] - startCoord[2]];
const dotProduct = pathLine[0] * startLine[0] + pathLine[1] * startLine[1];

if (dotProduct > 0)
{
    path[0] = userNode;
}
else
{
    path.unshift(userNode);
}

const cameraLineVector = [path[1].coord[0] - path[0].coord[0], path[1].coord[2] - path[0].coord[2]];
const vectorLength = Math.sqrt(cameraLineVector[0] ** 2 + cameraLineVector[1] ** 2);
cameraLineVector[0] /= vectorLength;
cameraLineVector[1] /= vectorLength;

camera.position.x = startCoord[0] - cameraLineVector[0] * 1
camera.position.y = 3;
camera.position.z = startCoord[2] - cameraLineVector[1] * 1;
camera.lookAt(startCoord[0] + cameraLineVector[0] * 2, 0, startCoord[2] + cameraLineVector[1] * 2);

/*
camera.position.x = scaleFactor * ratio / 2;
camera.position.y = 10;
camera.position.z = scaleFactor / 2;
camera.lookAt(scaleFactor * ratio / 2, 0, scaleFactor / 2);
*/

const pathMaterial = new THREE.LineBasicMaterial( {color:0xff1111});
for (var i = 0; i < path.length - 1; i++)
{
    const points = [];
    var coord = path[i].coord;
    points.push(new THREE.Vector3(coord[0], coord[1] + 0.01, coord[2]));
    coord = path[i + 1].coord;
    points.push(new THREE.Vector3(coord[0], coord[1] + 0.01, coord[2]));
    const lineGeometry = new THREE.BufferGeometry().setFromPoints( points );
    const line = new THREE.Line(lineGeometry, pathMaterial);
    scene.add(line);
}

function animate() {
    requestAnimationFrame( animate );

    renderer.render( scene, camera );
};

animate();