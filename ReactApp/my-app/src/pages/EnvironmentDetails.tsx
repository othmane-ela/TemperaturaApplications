import {useParams,useLocation } from 'react-router-dom';
import EnvironmentBox from './../components/EnvironmentBox'
import NodeList from './../components/NodeList'

function EnvironmentDetails() {

    let { envId } = useParams();
    const location = useLocation();


    return (
        <div>
            <EnvironmentBox envData ={location.state}/>
            <NodeList env={location.state} />
        </div>
    );
}

export default EnvironmentDetails;