(this.webpackJsonpfrontend=this.webpackJsonpfrontend||[]).push([[0],{127:function(e,t,n){},128:function(e,t,n){},204:function(e,t,n){"use strict";n.r(t);var c=n(0),i=n.n(c),s=n(30),a=n.n(s),o=(n(127),n(88)),r=n(107),l=function(e){if(e.ok)return e;var t=new Error(e.statusText);return t.response=e,Promise.reject(t)},j=n(209),d=n(37),u=n(112),b=n(41),O=n(211),x=n(212),h=n(210),m=n(213),g=n(214),f=n(215),p=n(216),y=n(217),I=(n(128),n(12)),k=j.a.Header,S=j.a.Content,v=j.a.Sider,N=d.a.SubMenu,w=[{title:"Id",dataIndex:"id",key:"id"},{title:"Building Number",dataIndex:"buildingNumber",key:"buildingNumber"},{title:"Description",dataIndex:"description",key:"description"},{title:"Public Access",dataIndex:"publicAccess",key:"publicAccess"}],B=Object(I.jsx)(h.a,{style:{fontSize:24},spin:!0});var C=function(){var e=Object(c.useState)([]),t=Object(o.a)(e,2),n=t[0],i=t[1],s=Object(c.useState)(!1),a=Object(o.a)(s,2),h=a[0],C=a[1],F=Object(c.useState)(!0),T=Object(o.a)(F,2),A=T[0],P=T[1],E=function(){return Object(r.a)("/buildings").then(l).then((function(e){return e.json()})).then((function(e){console.log(e),i(e),P(!1)}))};return Object(c.useEffect)((function(){console.log("component is mounted"),E()}),[]),Object(I.jsxs)(j.a,{style:{minHeight:"100vh"},children:[Object(I.jsxs)(v,{collapsible:!0,collapsed:h,onCollapse:function(){return C(!h)},style:{overflow:"auto",height:"100vh",position:"sticky",top:0,left:0},children:[Object(I.jsx)("div",{className:"logo"}),Object(I.jsxs)(d.a,{theme:"dark",defaultSelectedKeys:["1"],mode:"inline",children:[Object(I.jsx)(d.a.Item,{icon:Object(I.jsx)(m.a,{}),children:"Rooms"},"1"),Object(I.jsx)(d.a.Item,{icon:Object(I.jsx)(g.a,{}),children:"Meetings"},"2"),Object(I.jsx)(N,{icon:Object(I.jsx)(f.a,{}),title:"User",children:Object(I.jsx)(d.a.Item,{children:"Admin"},"3")},"sub1"),Object(I.jsx)(N,{icon:Object(I.jsx)(p.a,{}),title:"Team",children:Object(I.jsx)(d.a.Item,{children:"Team"},"6")},"sub2"),Object(I.jsx)(d.a.Item,{icon:Object(I.jsx)(y.a,{}),children:"Files"},"9")]})]}),Object(I.jsxs)(j.a,{className:"site-layout",children:[Object(I.jsx)(k,{className:"site-layout-background",style:{padding:0}}),Object(I.jsxs)(S,{style:{margin:"0 16px"},children:[Object(I.jsxs)(x.a,{style:{margin:"16px 0"},children:[Object(I.jsx)(x.a.Item,{children:"Buildings"}),Object(I.jsx)(x.a.Item,{children:"List"})]}),Object(I.jsx)("div",{className:"site-layout-background",style:{padding:24,minHeight:360},children:A?Object(I.jsx)(u.a,{indicator:B}):n.length<=0?Object(I.jsx)(b.a,{}):Object(I.jsx)(O.a,{dataSource:n,columns:w,bordered:!0,title:function(){return"Buildings"},pagination:{pageSize:50},scroll:{y:240},rowKey:function(e){return e.id}})})]})]})]})},F=function(e){e&&e instanceof Function&&n.e(3).then(n.bind(null,218)).then((function(t){var n=t.getCLS,c=t.getFID,i=t.getFCP,s=t.getLCP,a=t.getTTFB;n(e),c(e),i(e),s(e),a(e)}))};a.a.render(Object(I.jsx)(i.a.StrictMode,{children:Object(I.jsx)(C,{})}),document.getElementById("root")),F()}},[[204,1,2]]]);
//# sourceMappingURL=main.b626d4a5.chunk.js.map