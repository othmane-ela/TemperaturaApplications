import {  useState,useEffect,SyntheticEvent} from 'react';
import {
  Box,
  Heading,
  Text,
  Badge,
  Stack,
  Container,
  Avatar,useDisclosure,Button,FormControl,FormLabel,Input,
  useColorModeValue,Modal,ModalOverlay,ModalContent,ModalHeader,ModalFooter,ModalCloseButton,ModalBody
} from '@chakra-ui/react';

export default function NodeList({env}) {

    return (
    <Box bg={useColorModeValue('gray.100', 'gray.700')}>
      <Container maxW={'7xl'} py={16} as={Stack} spacing={12}>
        <Stack spacing={0} align={'center'}>
          <Heading>Our Installed Nodes</Heading>
          <Text>We have been working with clients around the world</Text>
        </Stack>
        <Stack spacing={0} align={'left'}>
          <AddNode envId={env.id}/>
        </Stack>
        <Stack  spacing={{ base: 10, md: 4, lg: 10 }}>
            {JSON.stringify(env.nodes)}
           { env.nodes?.map(node => { <NodeComponenet node={node}/>})  }
        </Stack>
      </Container>
    </Box>
  );
}

function NodeComponenet({node}){
   return (
   <Box  p={5}  bg={'gray.300'}>
   <Avatar src={ 'https://www.zoneindustrie.com/var/zoneindustrie/storage/images/entreprises/jumo-regulation/capteur-de-temperature-sans-fil-9940.html/2084731-1-fre-FR/Capteur-de-temperature-sans-fil_visuel_big.jpg'} />
     <Text fontWeight='bold'>
        {node.name}  {node.position}
       <Badge ml='1' colorScheme='green'>
         New 
       </Badge>
     </Text>
     <Text fontSize='sm'>DHT 11</Text>
   </Box>)
}



function AddNode({envId}) {

  const { isOpen, onOpen, onClose } = useDisclosure();
  const [name,setName] = useState('');
  const [position,setPosition] = useState('');

     const submit = async (e:SyntheticEvent) =>{
        e.preventDefault();
        const response = await fetch(`http://localhost:8080/api/v1/node/${envId}`,{  method:'POST',  
        headers:{'Content-Type':'application/json','Authorization': 'Bearer ' + localStorage.getItem('key')}, 
        body:JSON.stringify({
            name,position
      })
      }).then(res => {
        if (res.ok) {
          console.log(res.json())
          return res.json();
        } else {
          throw new Error('Something went wrong');
        }
      })
      .catch(error => console.log(error))
  
    }

  return (
    <>
      <Box>
        <Button bg={'blue.500'} color={'white'} mt={'3'} onClick={onOpen}>+ Add New Node</Button>
      </Box>

      <Modal isOpen={isOpen} onClose={onClose}>
        <ModalOverlay />
        <ModalContent>
          <form onSubmit={submit}>
          <ModalHeader>Modal Title</ModalHeader>
          <ModalCloseButton />
          <ModalBody>

          <FormControl id="Node Name">
              <FormLabel>Node Name </FormLabel>
              <Input type="text"  onChange={e => setName(e.target.value)} />
            </FormControl>

            <FormControl id="minTemperature">
              <FormLabel>Position </FormLabel>
              <Input type="text"  onChange={e => setPosition(e.target.value)} />
            </FormControl>

          </ModalBody>
          <ModalFooter>
            <Button colorScheme='blue' mr={3} onClick={onClose}>
              Close
            </Button>
            <Button variant='ghost' type='submit'>Add</Button>
          </ModalFooter>
          </form>
        </ModalContent>
      </Modal>
    </>
  )
}