import { PriorityQueue } from './priority_queue'
class Node
{
    constructor(name, type, coord)
    {
        this.name = name;
        this.type = type;
        this.coord = coord;
        this.adjList = [];
    }

    toString()
    {
        return this.name;
    }
}

class NavigationGraph
{
    constructor(json=null)
    {
        this.adjList = {};
        this.nameMap = {};
        if (json)
        {
            this.fromJson(json);
        }
    }

    addNode(node)
    {
        if (!(node.name in this.adjList))
        {
            this.adjList[node.name] = [];
            this.nameMap[node.name] = node;
        }
    }

    getNode(name)
    {
        return this.nameMap[name];
    }

    getNodes()
    {
        return Object.values(this.nameMap);
    }

    addEdge(node1, node2, weight)
    {
        this.adjList[node1].push({'node': node2, 'weight': weight});
    }

    fromJson(json)
    {
        json.nodes.forEach((node, index, array) => {
            this.addNode(new Node(node.name, node.type, node.coord));
        });
        json.edges.forEach((edge, index, array) => {
            this.addEdge(this.getNode(edge[0]), this.getNode(edge[1]), edge[2]);
        });
    }
}

function findRoute(graph, startName, endName)
{
    const pq = new PriorityQueue((a, b) => {b[0] - a[0]});
    const distance = {};
    const pi = {};
    const inf = 1000000000;

    graph.getNodes().forEach((node, index, array) => {
        distance[node.name] = inf;
        pi[node.name] = null;
    });

    const start = graph.getNode(startName);
    distance[start.name] = 0;
    pq.insert([0, start]);
    while (!pq.isEmpty())
    {
        const top = pq.removeMin();
        const cost = top[0];
        const node = top[1];
        if (distance[node.name] < cost)
        {
            continue;
        }

        var adj = graph.adjList[node];
        for (var i = 0; i < adj.length; i++)
        {
            const adjNode = adj[i].node;
            const nextDist = cost + adj[i].weight;
            if (distance[adjNode.name] > nextDist)
            {
                distance[adjNode.name] = nextDist;
                pq.insert([nextDist, adjNode]);
                pi[adjNode.name] = node;
            }
        }
    }
    
    var path = [];
    var end = graph.getNode(endName);
    path.push(end);
    while (pi[end.name] != null)
    {
        end = pi[end.name];
        path.push(end);
    }

    return path.reverse();
}

function findNeighbor(graph, coord)
{
    const nodes = graph.getNodes();
    var distance = 1000;
    var neighbor = null;
    nodes.forEach((node, index, array) =>{
        var tempDistance = Math.sqrt((coord[0] - node.coord[0])**2 + (coord[2] - node.coord[2])**2);
        if (tempDistance < distance)
        {
            neighbor = node;
            distance = tempDistance;
        }
    });

    return neighbor;
}

export { Node, NavigationGraph, findRoute, findNeighbor };