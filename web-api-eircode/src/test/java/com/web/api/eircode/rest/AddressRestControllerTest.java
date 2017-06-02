package com.web.api.eircode.rest;

import com.web.api.eircode.WebApiApplication;
import com.web.api.eircode.model.Address;
import com.web.api.eircode.service.AddressService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created by Flavio on 24/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApiApplication.class)
@AutoConfigureMockMvc
public class AddressRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void requiredPathRest() throws Exception {
        this.mockMvc.perform(get("/pcw/ /address/ie/D02X285" ) ).andExpect(status().isUnauthorized());
        this.mockMvc.perform(get("/pcw/XXXX/address/ /D02X285" ) ).andExpect(status().isBadRequest());
        this.mockMvc.perform(get("/pcw/XXXX/address/ie/ " ) ).andExpect(status().isBadRequest());
    }

    @Test
    public void optionalAllParametersRest() throws Exception {
        when(addressService.address(anyString(), anyString(), anyString(), anyInt(), anyString(),anyString(), anyString(),
                anyString(), anyString(), anyString(), anyInt(), anyBoolean(), anyBoolean()))
                .thenReturn(AddressRestControllerTest.framegmentAddressJson());
        this.mockMvc.perform(get("/pcw/PCW45-12345-12345-1234X/address/uk/manor%20farm%20barns" +
                "?format=json" +
                "&lines=1" +
                "&page=2" +
                "&include=something-1" +
                "&exclude=something-2" +
                "&addtags=something-3" +
                "&identifier=something-4" +
                "&callback=feedback" +
                "&postcodeonly=true" +
                "&alias=true" ) )
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType("application", "json")));
    }

    @Test
    public void responseContentTypeResponseRest() throws Exception {

        /*Address address = AddressRestControllerTest.bulderAddress("PCW45-12345-12345-1234X", "uk",
                "manor farm barns", null, null,  null,"json",
                null, null, null, null, null, null);
        */
        when(addressService.address(anyString(), anyString(), anyString(), anyInt(), anyString(),anyString(), anyString(),
                anyString(), anyString(), anyString(), anyInt(), anyBoolean(), anyBoolean()))
                .thenReturn(AddressRestControllerTest.framegmentAddressXML());

        this.mockMvc.perform(get("/pcw/PCW45-12345-12345-1234X/address/uk/manor%20farm%20barns?format=xml" ) )
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType("application", "xml")));

        when(addressService.address(anyString(), anyString(), anyString(), anyInt(), anyString(),anyString(), anyString(),
                anyString(), anyString(), anyString(), anyInt(), anyBoolean(), anyBoolean()))
                .thenReturn(AddressRestControllerTest.framegmentAddressJson());
        this.mockMvc.perform(get("/pcw/PCW45-12345-12345-1234X/address/uk/manor%20farm%20barns?format=json" ) )
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType("application", "json")));
    }


    private static String framegmentAddressJson(){
        return "[{\"summaryline\":\"Allies Computing Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"organisation\":\"Allies Computing Ltd\",\"buildingname\":\"Manor Farm Barns\",\"premise\":\"Manor Farm Barns\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"B 2 B Cashflow Solutions Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"organisation\":\"B 2 B Cashflow Solutions Ltd\",\"buildingname\":\"Manor Farm Barns\",\"premise\":\"Manor Farm Barns\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Brasteds Event Excellence, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"organisation\":\"Brasteds Event Excellence\",\"buildingname\":\"Manor Farm Barns\",\"premise\":\"Manor Farm Barns\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Brasteds Lodge, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"organisation\":\"Brasteds Lodge\",\"buildingname\":\"Manor Farm Barns\",\"premise\":\"Manor Farm Barns\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Conker Interiors Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"organisation\":\"Conker Interiors Ltd\",\"buildingname\":\"Manor Farm Barns\",\"premise\":\"Manor Farm Barns\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Eastern Chauffeurs Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"organisation\":\"Eastern Chauffeurs Ltd\",\"buildingname\":\"Manor Farm Barns\",\"premise\":\"Manor Farm Barns\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Equal Lives, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"organisation\":\"Equal Lives\",\"buildingname\":\"Manor Farm Barns\",\"premise\":\"Manor Farm Barns\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Estate Office, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"organisation\":\"Estate Office\",\"buildingname\":\"Manor Farm Barns\",\"premise\":\"Manor Farm Barns\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Framingham Pigot Parish Meeting, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"organisation\":\"Framingham Pigot Parish Meeting\",\"buildingname\":\"Manor Farm Barns\",\"premise\":\"Manor Farm Barns\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Genesis Lifts Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"organisation\":\"Genesis Lifts Ltd\",\"buildingname\":\"Manor Farm Barns\",\"premise\":\"Manor Farm Barns\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Mancroft International, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"organisation\":\"Mancroft International\",\"buildingname\":\"Manor Farm Barns\",\"premise\":\"Manor Farm Barns\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Paradox, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"organisation\":\"Paradox\",\"buildingname\":\"Manor Farm Barns\",\"premise\":\"Manor Farm Barns\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Serenity Training Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"organisation\":\"Serenity Training Ltd\",\"buildingname\":\"Manor Farm Barns\",\"premise\":\"Manor Farm Barns\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Variblast Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"organisation\":\"Variblast Ltd\",\"buildingname\":\"Manor Farm Barns\",\"premise\":\"Manor Farm Barns\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"(Potter), Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"buildingname\":\"(Potter)\",\"premise\":\"(Potter)\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Beck Cottage, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"buildingname\":\"Beck Cottage\",\"premise\":\"Beck Cottage\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Burnside, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"buildingname\":\"Burnside\",\"premise\":\"Burnside\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Charles Cottage, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"buildingname\":\"Charles Cottage\",\"premise\":\"Charles Cottage\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Cottage-On-The-Beck, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"buildingname\":\"Cottage-On-The-Beck\",\"premise\":\"Cottage-On-The-Beck\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Foxes Cottage, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"buildingname\":\"Foxes Cottage\",\"premise\":\"Foxes Cottage\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Framingham Cottage, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"buildingname\":\"Framingham Cottage\",\"premise\":\"Framingham Cottage\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"buildingname\":\"Manor Farm Barns\",\"premise\":\"Manor Farm Barns\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"Old Manor Farm, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"buildingname\":\"Old Manor Farm\",\"premise\":\"Old Manor Farm\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"1 Penns Cottages, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"buildingname\":\"1 Penns Cottages\",\"premise\":\"1 Penns Cottages\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"2 Penns Cottages, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"buildingname\":\"2 Penns Cottages\",\"premise\":\"2 Penns Cottages\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"The Grange, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"buildingname\":\"The Grange\",\"premise\":\"The Grange\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"1 Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"number\":\"1\",\"premise\":\"1\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"},{\"summaryline\":\"2 Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\"number\":\"2\",\"premise\":\"2\",\"street\":\"Fox Road\",\"dependentlocality\":\"Framingham Pigot\",\"posttown\":\"Norwich\",\"county\":\"Norfolk\",\"postcode\":\"NR14 7PZ\"}]";
    }

    private static String framegmentAddressXML(){
        return "<Addresses xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Allies Computing Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<organisation>Allies Computing Ltd</organisation>\n" +
                "<buildingname>Manor Farm Barns</buildingname>\n" +
                "<premise>Manor Farm Barns</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "B 2 B Cashflow Solutions Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<organisation>B 2 B Cashflow Solutions Ltd</organisation>\n" +
                "<buildingname>Manor Farm Barns</buildingname>\n" +
                "<premise>Manor Farm Barns</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Brasteds Event Excellence, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<organisation>Brasteds Event Excellence</organisation>\n" +
                "<buildingname>Manor Farm Barns</buildingname>\n" +
                "<premise>Manor Farm Barns</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Brasteds Lodge, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<organisation>Brasteds Lodge</organisation>\n" +
                "<buildingname>Manor Farm Barns</buildingname>\n" +
                "<premise>Manor Farm Barns</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Conker Interiors Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<organisation>Conker Interiors Ltd</organisation>\n" +
                "<buildingname>Manor Farm Barns</buildingname>\n" +
                "<premise>Manor Farm Barns</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Eastern Chauffeurs Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<organisation>Eastern Chauffeurs Ltd</organisation>\n" +
                "<buildingname>Manor Farm Barns</buildingname>\n" +
                "<premise>Manor Farm Barns</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Equal Lives, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<organisation>Equal Lives</organisation>\n" +
                "<buildingname>Manor Farm Barns</buildingname>\n" +
                "<premise>Manor Farm Barns</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Estate Office, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<organisation>Estate Office</organisation>\n" +
                "<buildingname>Manor Farm Barns</buildingname>\n" +
                "<premise>Manor Farm Barns</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Framingham Pigot Parish Meeting, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<organisation>Framingham Pigot Parish Meeting</organisation>\n" +
                "<buildingname>Manor Farm Barns</buildingname>\n" +
                "<premise>Manor Farm Barns</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Genesis Lifts Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<organisation>Genesis Lifts Ltd</organisation>\n" +
                "<buildingname>Manor Farm Barns</buildingname>\n" +
                "<premise>Manor Farm Barns</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Mancroft International, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<organisation>Mancroft International</organisation>\n" +
                "<buildingname>Manor Farm Barns</buildingname>\n" +
                "<premise>Manor Farm Barns</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Paradox, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<organisation>Paradox</organisation>\n" +
                "<buildingname>Manor Farm Barns</buildingname>\n" +
                "<premise>Manor Farm Barns</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Serenity Training Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<organisation>Serenity Training Ltd</organisation>\n" +
                "<buildingname>Manor Farm Barns</buildingname>\n" +
                "<premise>Manor Farm Barns</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Variblast Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<organisation>Variblast Ltd</organisation>\n" +
                "<buildingname>Manor Farm Barns</buildingname>\n" +
                "<premise>Manor Farm Barns</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "(Potter), Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<buildingname>(Potter)</buildingname>\n" +
                "<premise>(Potter)</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Beck Cottage, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<buildingname>Beck Cottage</buildingname>\n" +
                "<premise>Beck Cottage</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Burnside, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<buildingname>Burnside</buildingname>\n" +
                "<premise>Burnside</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Charles Cottage, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<buildingname>Charles Cottage</buildingname>\n" +
                "<premise>Charles Cottage</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Cottage-On-The-Beck, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<buildingname>Cottage-On-The-Beck</buildingname>\n" +
                "<premise>Cottage-On-The-Beck</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Foxes Cottage, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<buildingname>Foxes Cottage</buildingname>\n" +
                "<premise>Foxes Cottage</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Framingham Cottage, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<buildingname>Framingham Cottage</buildingname>\n" +
                "<premise>Framingham Cottage</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<buildingname>Manor Farm Barns</buildingname>\n" +
                "<premise>Manor Farm Barns</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "Old Manor Farm, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<buildingname>Old Manor Farm</buildingname>\n" +
                "<premise>Old Manor Farm</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "1 Penns Cottages, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<buildingname>1 Penns Cottages</buildingname>\n" +
                "<premise>1 Penns Cottages</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "2 Penns Cottages, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<buildingname>2 Penns Cottages</buildingname>\n" +
                "<premise>2 Penns Cottages</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "The Grange, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<buildingname>The Grange</buildingname>\n" +
                "<premise>The Grange</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "1 Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<number>1</number>\n" +
                "<premise>1</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "<Address>\n" +
                "<summaryline>\n" +
                "2 Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\n" +
                "</summaryline>\n" +
                "<number>2</number>\n" +
                "<premise>2</premise>\n" +
                "<street>Fox Road</street>\n" +
                "<dependentlocality>Framingham Pigot</dependentlocality>\n" +
                "<posttown>Norwich</posttown>\n" +
                "<county>Norfolk</county>\n" +
                "<postcode>NR14 7PZ</postcode>\n" +
                "</Address>\n" +
                "</Addresses>";
    }


    private static Address bulderAddress(final String apiKey, final String country, final String addressCode,
                                             final Integer lines, final String include, final String exclude, final String format,
                                             final String addtags, final String identifier, final String callback, final Integer page,
                                             final Boolean postcodeonly, final Boolean alias) {

        return new Address.Builder()
                .withAddressCode(addressCode)
                .withAddtags(addtags)
                .withAlias(alias)
                .withCallback(callback)
                .withCountry(country)
                .withExclude(exclude)
                .withFormat(format)
                .withIdentifier(identifier)
                .withInclude(include)
                .withLines(lines)
                .withPage(page)
                .withPostcodeonly(postcodeonly)
                .build();
    }






}
