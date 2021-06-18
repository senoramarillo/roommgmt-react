import {useState, useEffect} from 'react';
import {getAllBuildings} from "./client";
import {
    Layout,
    Menu,
    Breadcrumb,
    Table, Spin, Empty
} from 'antd';
import {
    DesktopOutlined,
    PieChartOutlined,
    FileOutlined,
    TeamOutlined,
    UserOutlined,
    LoadingOutlined
} from '@ant-design/icons';

import './App.css';

const {Header, Content, Sider} = Layout;
const {SubMenu} = Menu;

const columns = [
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
];

const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;

function App() {
    const [buildings, setBuildings] = useState([]);
    const [collapsed, setCollapsed] = useState(false);
    const [fetching, setFetching] = useState(true);

    const fetchBuildings = () =>
        getAllBuildings()
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setBuildings(data);
                setFetching(false);
            });

    useEffect(() => {
        console.log("component is mounted");
        fetchBuildings();
    }, []);

    const renderBuildings = () => {

        if (fetching) {
            return <Spin indicator={antIcon} />
        }

        if (buildings.length <= 0) {
            return <Empty />;
        }

        return <Table
            dataSource={buildings}
            columns={columns}
            bordered
            title={() => 'Buildings'}
            pagination={{ pageSize: 50 }}
            scroll={{ y: 240 }}
            rowKey={(building) => building.id}
        />
    }

    return <Layout style={{minHeight: '100vh'}}>
        <Sider collapsible collapsed={collapsed}
               onCollapse={setCollapsed}>
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
