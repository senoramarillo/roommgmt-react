import {useState, useEffect} from 'react';
import {deleteBuilding, getAllBuildings} from "./client";

import {
    Layout,
    Menu,
    Breadcrumb,
    Table,
    Spin,
    Empty,
    Button,
    Tag,
    Badge,
    Radio,
    Popconfirm
} from 'antd';

import {
    DesktopOutlined,
    PieChartOutlined,
    FileOutlined,
    TeamOutlined,
    UserOutlined,
    LoadingOutlined, PlusOutlined
} from '@ant-design/icons';

import BuildingDrawerForm from "./BuildingDrawerForm";
import './App.css';
import {errorNotification, successNotification} from "./Notification";

const {Header, Content, Sider} = Layout;
const {SubMenu} = Menu;

const removeBuilding = (buildingId, callback) => {
    deleteBuilding(buildingId).then(() => {
        successNotification("Building deleted", `Building with ${buildingId} was deleted`);
        callback();
    }).catch(err => {
        err.response.json().then(res => {
            console.log(res);
            errorNotification(
                "There was an issue",
                `${res.message} [${res.status}] [${res.error}]`
            )
        });
    })
}

const columns = fetchBuildings => [
    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
    },
    {
        title: 'Building Number',
        dataIndex: 'buildingNumber',
        key: 'buildingNumber',
    },
    {
        title: 'Description',
        dataIndex: 'description',
        key: 'description',
    },
    {
        title: 'Public Access',
        dataIndex: 'publicAccess',
        key: 'publicAccess',
    },
    {
        title: 'Actions',
        key: 'actions',
        render: (text, building) =>
            <Radio.Group>
                <Popconfirm
                    placement='topRight'
                    title={`Are you sure to delete ${building.description}`}
                    onConfirm={() => removeBuilding(building.id, fetchBuildings)}
                    okText='Yes'
                    cancelText='No'>
                    <Radio.Button value="small">Delete</Radio.Button>
                </Popconfirm>
                <Radio.Button onClick={() => alert("TODO: Implement edit building")} value="small">Edit</Radio.Button>
            </Radio.Group>
    }
];

const antIcon = <LoadingOutlined style={{fontSize: 24}} spin/>;

function App() {
    const [buildings, setBuildings] = useState([]);
    const [collapsed, setCollapsed] = useState(false);
    const [fetching, setFetching] = useState(true);
    const [showDrawer, setShowDrawer] = useState(false);

    const fetchBuildings = () =>
        getAllBuildings()
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setBuildings(data);
            }).catch(err => {
            console.log(err.response)
            err.response.json().then(res => {
                console.log(res);
                errorNotification(
                    "There was an issue",
                    `${res.message} [${res.status}] [${res.error}]`
                )
            });
        }).finally(() => setFetching(false))


    useEffect(() => {
        console.log("component is mounted");
        fetchBuildings();
    }, []);

    const renderBuildings = () => {

        if (fetching) {
            return <Spin indicator={antIcon}/>
        }

        if (buildings.length <= 0) {
            return <>
                <Button
                    onClick={() => setShowDrawer(!showDrawer)}
                    type="primary" shape="round" icon={<PlusOutlined/>} size="small">
                    Add New Student
                </Button>
                <BuildingDrawerForm
                    showDrawer={showDrawer}
                    setShowDrawer={setShowDrawer}
                    fetchBuildings={fetchBuildings}
                />
                <Empty/>
            </>
        }

        return <>
            <BuildingDrawerForm
                showDrawer={showDrawer}
                setShowDrawer={setShowDrawer}
            />

            <Table
                dataSource={buildings}
                columns={columns(fetchBuildings)}
                bordered
                title={() =>
                    <>
                        <Tag>Number of buildings</Tag>
                        <Badge count={buildings.length} className="site-badge-count-4"/>
                        <br/><br/>
                        <Button
                            onClick={() => setShowDrawer(!showDrawer)}
                            type="primary" shape="round" icon={<PlusOutlined/>} size="small">
                            Add New Building
                        </Button>
                    </>
                }
                pagination={{pageSize: 50}}
                scroll={{y: 500}}
                rowKey={(building) => building.id}
            />
        </>
    }

    return <Layout style={{minHeight: '100vh'}}>
        <Sider collapsible
               collapsed={collapsed}
               onCollapse={() => setCollapsed(!collapsed)}
               style={{
                   overflow: "auto",
                   height: "100vh",
                   position: "sticky",
                   top: 0,
                   left: 0
               }}>
            <div className="logo"/>
            <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline">
                <Menu.Item key="1" icon={<PieChartOutlined/>}>
                    Rooms
                </Menu.Item>
                <Menu.Item key="2" icon={<DesktopOutlined/>}>
                    Meetings
                </Menu.Item>
                <SubMenu key="sub1" icon={<UserOutlined/>} title="User">
                    <Menu.Item key="3">Admin</Menu.Item>
                </SubMenu>
                <SubMenu key="sub2" icon={<TeamOutlined/>} title="Team">
                    <Menu.Item key="6">Team</Menu.Item>
                </SubMenu>
                <Menu.Item key="9" icon={<FileOutlined/>}>
                    Files
                </Menu.Item>
            </Menu>
        </Sider>
        <Layout className="site-layout">
            <Header className="site-layout-background" style={{padding: 0}}/>
            <Content style={{margin: '0 16px'}}>
                <Breadcrumb style={{margin: '16px 0'}}>
                    <Breadcrumb.Item>Buildings</Breadcrumb.Item>
                    <Breadcrumb.Item>List</Breadcrumb.Item>
                </Breadcrumb>
                <div className="site-layout-background" style={{padding: 24, minHeight: 360}}>
                    {renderBuildings()}
                </div>
            </Content>
        </Layout>
    </Layout>
}

export default App;
