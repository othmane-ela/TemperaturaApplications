import {
    Box,
    chakra,
    Flex,
    SimpleGrid,
    Stat,
    StatLabel,
    StatNumber,
    useColorModeValue,
  } from '@chakra-ui/react';
  import { ReactNode } from 'react';
  import { GoLocation } from 'react-icons/go';
  import { Link as RouterLink } from "react-router-dom";
  
  interface StatsCardProps {
    id:number,
    name: string;
    maxHumidity: number;
    minHumidity: number;
    maxTemperature:number
    minTemperature:number
    icon: ReactNode;
  }
  function StatsCard(props: StatsCardProps) {
    const { id,name, maxHumidity,minHumidity,maxTemperature,minTemperature, icon } = props;
    return (
      <RouterLink to={`/dashboard/environment/${id}`}>
      <Stat
        px={{ base: 2, md: 4 }}
        py={'5'}
        shadow={'xl'}
        border={'1px solid'}
        borderColor={useColorModeValue('gray.800', 'gray.500')}
        rounded={'lg'}>
        <Flex justifyContent={'space-between'}>
          <Box pl={{ base: 2, md: 4 }}>
            <StatLabel  isTruncated  fontWeight={'bold'}>
              {name}
            </StatLabel>
            <StatNumber fontSize={'1xl'} fontWeight={'medium'} color={'red.500'}>
               min Temperature  : {minTemperature} c° 
            </StatNumber>
            <StatNumber fontSize={'1xl'} fontWeight={'medium'} color={'blue.500'}>
              max Temperature  : {maxTemperature} c° 
            </StatNumber>

            <StatNumber fontSize={'1xl'} fontWeight={'medium'} color={'red.400'}>
               min Humidity  : {minHumidity} % 
            </StatNumber>
            <StatNumber fontSize={'1xl'} fontWeight={'medium'} color={'blue.400'}>
              max Humidity  : {maxHumidity} % 
            </StatNumber>
          </Box>
          <Box
            my={'auto'}
            color={useColorModeValue('gray.800', 'gray.200')}
            alignContent={'center'}>
            {icon}
          </Box>
        </Flex>
      </Stat>
      </RouterLink>
    );
  }
  
  export default function BasicStatistics({data}) {
    return (
    
      <Box maxW="7xl" mx={'auto'} my={'6'} pt={1} px={{ base: 2, sm: 12, md: 17 }}>
   
        <SimpleGrid columns={{ base: 1, md: 3 }} spacing={{ base: 5, lg: 8 }}>
      
        {data.map(env => 
            <StatsCard
             id={env.id}
              key={env.id}
              name={env.name}
              maxHumidity ={env.maxHumidity}
              minHumidity ={env.minHumidity}
              maxTemperature={env.maxTemperature}
              minTemperature={env.minTemperature}
              icon={<GoLocation size={'3em'} />}
          />

          )}

        </SimpleGrid>
      </Box>


    );
  }