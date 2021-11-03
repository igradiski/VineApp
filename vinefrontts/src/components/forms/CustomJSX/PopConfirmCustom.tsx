import { Popconfirm} from 'antd';

const PopConfirmCustom  = (title:String,action:() =>void,icon:JSX.Element) => {
    return (
        <Popconfirm
            placement="top"
            title={title}
            onConfirm={() => {
                action();
            }}
            okText="Da"
            cancelText="Ne"
        >{icon}
        </Popconfirm>
    )
}
export default PopConfirmCustom;