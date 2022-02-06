import {useState,useEffect,SyntheticEvent} from 'react';
import {
    Modal,
    ModalOverlay,
    ModalContent,
    ModalHeader,
    ModalFooter,
    ModalBody,
    ModalCloseButton,
    Button,
   useDisclosure,Box,Stack,FormControl,FormLabel,Input,Checkbox,Link
  } from '@chakra-ui/react'
  import {Navigate} from 'react-router-dom'
import BasicStatistics from '../components/BasicStatistics';

function Environment() {

        const [env,setEnv] = useState([]);
        const [error,setError] = useState(null);

        useEffect(() => {
            fetch('http://localhost:8080/api/v1/environment/',{
              headers:{
                'Content-Type':'application/json',
                  'Authorization': 'Bearer ' + localStorage.getItem('key')
              }
            })
            .then(res => {
              if (res.ok) {
                return res.json();
              } else {
                throw new Error('Something went wrong');
              }
            })
            .then(data=> setEnv(data))
            .catch(error => setError(error))
            
          },[])


          const addEnvironement = (data) =>{
            setEnv(env => [...env, data])
            data = null;
            console.log(env)
      }
     
    return (
        <div>
                <NewEnvModal handleSubmit={addEnvironement}/>
                <BasicStatistics data={env} />
        </div>
    );
}

export default Environment;

function NewEnvModal({handleSubmit}){

    const { isOpen, onOpen, onClose } = useDisclosure()
    const [name,setName] = useState('');
    const [description,setDescription] = useState('');
    const [minHumidity,setMinHumidity] = useState('');
    const [maxHumidity,setMaxHumidity] = useState('');
    const [minTemperature,setMinTemperature] = useState('');
    const [maxTemperature,setMaxTemperature] = useState('');
    

    const submit = async (e:SyntheticEvent) =>{
        e.preventDefault();
        const response = await fetch('http://localhost:8080/api/v1/environment/',{  method:'POST',  
        headers:{'Content-Type':'application/json','Authorization': 'Bearer ' + localStorage.getItem('key')}, 
        body:JSON.stringify({
            name,
            description,minHumidity,maxHumidity,minTemperature,maxTemperature
      })
      }).then(res => {
        if (res.ok) {
          return res.json();
        } else {
          throw new Error('Something went wrong');
        }
      })
      .then(data=> onTrigger(data))
      .catch(error => console.log(error))
  
    }
     const onTrigger = (data) => {
        handleSubmit(data);
        data = null;
        onClose();
    }
  

    return (
      <>
        <Box maxW="7xl" mx={'auto'} my={'6'} pt={1} textAlign={'left'} px={{ base: 2, sm: 12, md: 17 }}>
          <Button  bg={'cyan.400'}  color={'white'} onClick={onOpen}>+ Add new Environment</Button>
        </Box>

        <Modal isOpen={isOpen} onClose={onClose}>
          <ModalOverlay />
          <ModalContent>
          <form onSubmit={submit}>
            <ModalHeader>Add new Environment</ModalHeader>
            <ModalCloseButton />
            <ModalBody>
          
            <Box
          rounded={'lg'}
          bg={'gray.50'}
          boxShadow={'lg'}
          p={8}>
          <Stack spacing={4}>


            <FormControl id="Name">
              <FormLabel>Name</FormLabel>
               <Input type="text" onChange={e => setName(e.target.value)}  />
            </FormControl>

            <FormControl id="description">
              <FormLabel>description</FormLabel>
            <Input type="text"  onChange={e => setDescription(e.target.value)} />
            </FormControl>

            <FormControl id="minTemperature">
              <FormLabel>Min Temperature </FormLabel>
            <Input type="number"  onChange={e => setMinTemperature(e.target.value)} />
            </FormControl>


              <FormControl id="maxTemperature">
              <FormLabel>Max Temperature </FormLabel>
             <Input type="number" onChange={e => setMaxTemperature(e.target.value)}  />
            </FormControl>


            <FormControl id="minHumidity">
              <FormLabel>Min Humidity</FormLabel>
            <Input type="number"  onChange={e => setMinHumidity(e.target.value)}  />
            </FormControl>


            <FormControl id="maxHumidity">
              <FormLabel>Max Humidity</FormLabel>
               <Input type="number"   onChange={e => setMaxHumidity(e.target.value)}  />
            </FormControl>

             </Stack>
            </Box>
          
            </ModalBody>
  
            <ModalFooter>
            <Button  type="submit">Add new</Button>
              <Button colorScheme='blue' mr={3} onClick={onClose}>
                Close
              </Button>
            </ModalFooter>
            </form>
          </ModalContent>
        </Modal>
      </>
    )
}


