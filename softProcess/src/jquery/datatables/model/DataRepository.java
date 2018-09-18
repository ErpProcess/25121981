package jquery.datatables.model;

import java.util.LinkedList;
import java.util.List;

public class DataRepository {
	
	/// <summary>
    /// Singleton collection of companies
    /// </summary>
    private static List<Company> CompanyData = null;

    /// <summary>
    /// Method that returns all companies used in this example
    /// </summary>
    /// <returns>List of companies</returns>
    public static List<Company> GetCompanies()
    {
        if (CompanyData == null)
        {
            CompanyData = new LinkedList<Company>();
            CompanyData.add(new Company("Emkay Entertainments", "Nobel House, Regent Centre", "Lothian" ));
            CompanyData.add(new Company("The Empire", "Milton Keynes Leisure Plaza", "Buckinghamshire" ));
            CompanyData.add(new Company("Asadul Ltd", "Hophouse", "Essex" ));
            CompanyData.add(new Company("Gargamel ltd", "", "" ));
            CompanyData.add(new Company("Ashley Mark Publishing Company", "1-2 Vance Court", "Tyne & Wear" ));
            CompanyData.add(new Company("MuchMoreMusic Studios", "Unit 29", "London" ));
        }
        return CompanyData;
    }

}
