import {useParams } from 'react-router-dom';


function EnvironmentDetails() {

    let { envId } = useParams();


    return (
        <div>
            EnvironmentDetails  N° : {envId}
        </div>
    );
}

export default EnvironmentDetails;